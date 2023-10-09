package com.banu.repository;


import com.banu.repository.entity.Post;
import com.banu.repository.entity.User;
import com.banu.repository.views.viewLikePost;
import com.banu.utility.HibernateUtility;
import com.banu.utility.MyFactoryRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class PostRepository extends MyFactoryRepository<Post,Long> {

    EntityManager entityManager;
    CriteriaBuilder criteriaBuilder;

    public PostRepository(){
        super(new Post());
        this.entityManager = HibernateUtility.getSessionFactory().createEntityManager();
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public List<viewLikePost> myLikePosts(User user) {
        String sql = "SELECT p.id,p.computerid,p.userid,p.likecount FROM tbl_like AS l INNER JOIN tbl_post AS p ON l.postid=p.id WHERE l.userid=:userid";
        TypedQuery<viewLikePost> result = (TypedQuery<viewLikePost>) entityManager.createNativeQuery(sql, viewLikePost.class);
        result.setParameter("userid",user.getId());
        return result.getResultList();

    }
    public List<Post> findByColumnNameAndValue(String columnname, Long value){
        CriteriaQuery<Post> criteria = (CriteriaQuery<Post>) criteriaBuilder.createQuery(Post.class);
        Root<Post> root = (Root<Post>) criteria.from(Post.class);
        criteria.select(root);
        criteria.where(criteriaBuilder.equal(root.get(columnname),value));
        return entityManager.createQuery(criteria).getResultList();
    }


}
