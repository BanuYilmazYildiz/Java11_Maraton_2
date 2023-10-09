package com.banu.utility;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MyFactoryRepository<T,ID> implements ICrud<T,ID> {
    private Session session;
    private Transaction transaction;
    private CriteriaBuilder criteriaBuilder;
    private EntityManager entityManager;

    T t;
    public MyFactoryRepository(T entity){
        entityManager = HibernateUtility.getSessionFactory().createEntityManager();
        criteriaBuilder = entityManager.getCriteriaBuilder();
        this.t = entity;
    }

    private void openSession(){
        session = HibernateUtility.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }
    private void closeSession(){
        transaction.commit();
        session.close();
    }


    @Override
    public T save(T entity) {
        openSession();
        session.save(entity);
        closeSession();
        return entity;
    }

    @Override
    public T update(T entity) {
        openSession();
        session.update(entity);
        closeSession();
        return entity;
    }

    @Override
    public Iterable<T> saveAll(Iterable<T> entites) {
        openSession();
        entites.forEach(t->{
            session.save(t);
        });
        closeSession();
        return entites;
    }

    @Override
    public void delete(T entity) {
        openSession();
        session.delete(entity);
        closeSession();

    }

    @Override
    public void deleteById(ID id) {
        CriteriaQuery<T> criteria = (CriteriaQuery<T>) criteriaBuilder.createQuery(t.getClass());
        Root<T> root = (Root<T>) criteria.from(t.getClass());
        criteria.select(root);
        criteria.where(criteriaBuilder.equal(root.get("id"),id));
        T result = entityManager.createQuery(criteria).getSingleResult();
        session.delete(result);
        closeSession();
    }

    /**
     * select * from tbl_ where id?
     * @param id
     * @return
     */
    @Override
    public Optional<T> findById(ID id) {
        CriteriaQuery<T> criteria = (CriteriaQuery<T>) criteriaBuilder.createQuery(t.getClass());
        Root<T> root = (Root<T>) criteria.from(t.getClass());
        criteria.select(root);
        criteria.where(criteriaBuilder.equal(root.get("id"),id));
        T result2 = entityManager.createQuery(criteria).getSingleResult();
        return Optional.ofNullable(result2);

//        List<T> result = entityManager.createQuery(criteria).getResultList();
//        if(result.isEmpty()){ // Eğer sorgu neticesinde hiv bir deger donmez ise bos olacak
//           return Optional.empty();
//        }
//        return Optional.of(result.get(0)); // En az bir deger donecektir. Ilk donen degeri Optional olarak don
    }

    @Override
    public boolean existById(ID id) {
        CriteriaQuery<T> criteria = (CriteriaQuery<T>) criteriaBuilder.createQuery(t.getClass());
        Root<T> root = (Root<T>) criteria.from(t.getClass());
        criteria.select(root);
        criteria.where(criteriaBuilder.equal(root.get("id"),id));
        T result = entityManager.createQuery(criteria).getSingleResult();
        return result!=null; // Eğer kayıt var ise (null değilse) --> true
    }

    /**
     * SELECT * FROM tbl_
     * @return
     */
    @Override
    public List<T> findALl() {
        CriteriaQuery<T> criteria = (CriteriaQuery<T>) criteriaBuilder.createQuery(t.getClass());
        Root<T> root = (Root<T>) criteria.from(t.getClass());
        criteria.select(root);
        return entityManager.createQuery(criteria).getResultList();
    }

    /**
     * SELECT * FROM tbl WHERE
     * @param columnName
     * @param value % ve _ karakterleri kullanıcı tarafından girilecektir
     * @return
     */
    @Override
    public List<T> findByColumnNameAndValue(String columnName, String value) {
        CriteriaQuery<T> criteria = (CriteriaQuery<T>) criteriaBuilder.createQuery(t.getClass());
        Root<T> root = (Root<T>) criteria.from(t.getClass());
        criteria.select(root);
        //criteria.where(criteriaBuilder.equal(root.get(columnName),value));
        criteria.where(criteriaBuilder.like(root.get(columnName),value)); // ikisi de kulanilabilir
        return entityManager.createQuery(criteria).getResultList();
    }

    /**
     * Burada yazılmak istenilen detay sudur. Bir sinif icindeki alan adları (degiskenler) yazılım tarafından
     * okunulacak ve bu değişkenkerin değerleri kontrol edilecel null olmayanlar sorguya dahil edilecel. Boylece
     * esnek sorgulama işlemi gerçekleştrilmiş olacak.
     * bu işlemi yapabilmek için Java Reflection Api kullanılacaktır
     *              Musteri musteri =new Musteri();
     *                  musteri.setAd("M");
     *                  musteri.setSoyad("Hoca");
     * SELECT * FROM tbl_musteri WHERE id LIKE '%M%' and sound LIKE '%HOCA%';
     *
     * @param entity
     * @return
     */
    @Override
    public List<T> findByEntity(T entity) {
        // Reflection islemidir
        List<T> result = null;
        Class cl = entity.getClass(); // entity nin class özelliklerini seviyorum
        Field[] fl = cl.getDeclaredFields(); //class içindeki tüğm değişkenleri bir liste içine alıyorum. id,ad,soyad,vs..
        try{
            CriteriaQuery<T> criteria = (CriteriaQuery<T>) criteriaBuilder.createQuery(t.getClass());
            Root<T> root = (Root<T>) criteria.from(t.getClass());
            criteria.select(root);
            List<Predicate> predicateList = new ArrayList<>(); // sorgu için gerekli kriterlerin listesini ekleyeceğimiz liste
            for(int i = 0;i<fl.length;i++){
                fl[i].setAccessible(true); // erişmek isteediğimiz alanların erişimini açıyoruz. Bunu unutursanız null olur
                /**
                 * okumakta olduğum alan null değil ise,
                 * ayrıca okuduğum alan id değil ise,
                 */
                if(fl[i].get(entity)!= null && !fl[i].get(entity).equals("id")){
                    /**
                     * Sorguları yazarken değişkenklerin tipi önemlidir. Mesela int bir değer için like kullanamazsınız
                     */
                    if (fl[i].getType().isAssignableFrom(String.class)){
                      predicateList.add(criteriaBuilder.like(root.get(fl[i].getName()),"%"+fl[i].get(entity)+"%"));
                    }
                    else if (fl[i].getType().isAssignableFrom(Number.class)){
                       predicateList.add(criteriaBuilder.equal(root.get(fl[i].getName()),fl[i].get(entity)));
                    }
                    else {
                        predicateList.add(criteriaBuilder.equal(root.get(fl[i].getName()),fl[i].get(entity)));
                    }
                }
            }

            criteria.where(predicateList.toArray(new Predicate[]{}));
            result=entityManager.createQuery(criteria).getResultList();

        }catch (Exception e){
            System.out.println("Beklenöeyen bir hata oldu...."+e.toString());
        }
        return result;
    }


}
