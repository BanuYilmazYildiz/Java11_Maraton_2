package com.banu;

import com.banu.controller.*;
import com.banu.repository.entity.User;
import com.banu.utility.UtilityClass;

public class Main {

    UserController userController;
    ComputerController computerController;
    ComputerSpecContoller computerSpecContoller;
    PostContoller postContoller;
    LikeController likeController;
    public Main(){
        this.userController=new UserController();
        this.computerController = new ComputerController();
        this.computerSpecContoller=new ComputerSpecContoller();
        this.postContoller = new PostContoller();
        this.likeController=new LikeController();
    }

    public static void main(String[] args) {

        Main main = new Main();
      
        main.uygulama();


    }

    public void uygulama(){
        boolean kontrol=true;
        do {
            int secim = menu();
            switch (secim){
                case 1:{
                    userController.createAccount();
                }break;
                case 2:{
                    loginControl();
                }break;
                case 0:{
                    kontrol=false;
                }break;
            }

        }while (kontrol);
    }

    public int menu(){
        System.out.println("******************");
        System.out.println("***   PC Show  ***");
        System.out.println("******************");
        System.out.println();
        System.out.println("1- Kayit Ol");
        System.out.println("2- Giris Yap");
        System.out.println("0- Cikis Yap");
        int secim = UtilityClass.intDeger("Lutfen bir secim yapiniz : ");
        return secim;
    }

    public User loginControl(){
        User user = userController.loginUser();
        if (user != null){
            registerMenu(user);
        }else {
            System.out.println("Kullanici adiniz hatali");
        }
        return user;
    }

    private void registerMenu(User user) {
        boolean kontrol=true;
        do {
            int secim = registerMenuGoruntusu();
            switch (secim) {
                case 1: {
                    computerSpecContoller.createSpec();
                }
                break;
                case 2: {
                    computerController.createComputer();
                }
                break;
                case 3: {
                    postContoller.newPost(user);
                }
                break;
                case 4: {
                    likeController.likePost(user);
                }
                break;
                case 5: {
                    postContoller.findAll().forEach(System.out::println);
                }
                break;
                case 6: {
                    postContoller.myLikePosts(user).forEach(System.out::println);
                }
                break;
                case 7: {
                    postContoller.findPostsByUserId().forEach(System.out::println);
                }
                break;
                case 0: {
                    kontrol = false;
                }
                break;
            }
        }while (kontrol);
    }

    private int registerMenuGoruntusu() {
        System.out.println("******************");
        System.out.println("***   PC Show  ***");
        System.out.println("******************");
        System.out.println();
        System.out.println("1- Bilgisayar Bileşeni oluştur ");
        System.out.println("2- Bilgisayar oluştur");
        System.out.println("3- Post At");
        System.out.println("4- Post a beğeni yap");
        System.out.println("5- Tüm postları listele");
        System.out.println("6- Beğendiğim gönderiler");
        System.out.println("7- Bir kişiye ait postları listele");
        System.out.println("0- Çıkış");
        int secim = UtilityClass.intDeger("Lutfen bir secim yapiniz : ");
        return secim;
    }
}