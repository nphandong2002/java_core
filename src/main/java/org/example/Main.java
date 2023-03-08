package org.example;

import org.example.Entity.CheckProduct;
import org.example.Entity.Product;
import org.example.Utils.Log;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    static List<Product> list = new ArrayList<>();
    private Log log = new Log();

    public  void logItemProduct(Product p){
        log( p.getId() + " " + p.getNameProduct() + " " + p.getNameManufacturers()+ " " + p.getType()+ " " + p.getPrice(),"","");
    }


    public  void showMenu(String a){
        if("".equals(a)){
            log(a,"","");
        }
        Log("============","yellow","");

        Log("1. View danh sách sản phẩm ","","");
        Log("2. Tìm kiếm sản phẩm theo các thông tin sản phẩm ","","");
        Log("3. Thêm mới sản phẩm theo id tăng dần ","","");
        Log("4. Update theo id sản phẩm ","","");
        Log("5. Xóa sản phẩm theo id ","","");
        Log("0. Thoat ","","");
        System.out.print("Chon: ");
    }
    public static void showProduct(int type){
        String[] sArr = new String[]{
                "1. ten ",
                "2. ten nsx ",
                "3. loai ",
                "4. tien ",
                "6. tro lai ",
        };
        if(type == 0){
            for (String s:sArr){
                System.out.println(s);
            }
            System.out.print("Chon: ");
        }else {
            System.out.printf(sArr[type-1]);
        }
    }

    public static int search(Scanner scanner){
        Log("Ban muon tim kiem theo ?","yellow","");
        System.out.println("0. id");
        showProduct(0);
        int chon;
        List<Product> productList = new ArrayList<>() ;
        do {
            chon = scanner.nextInt();
            if(chon != 0 )showProduct(chon);
            else System.out.printf("0.id ");
            scanner.nextLine();
            switch (chon){
                case 0:
                    int id = scanner.nextInt();
                    productList = list.stream().filter(item -> item.getId() == id).collect(Collectors.toList());
                    chon = 6;
                    break;
                case 1:
                    String name = scanner.nextLine();
                    productList = list.stream().filter(item -> item.getNameProduct().equals(name)).collect(Collectors.toList());
                    chon = 6;

                    break;
                case 2:
                    String namensx = scanner.nextLine();
                    productList = list.stream().filter(item -> item.getNameManufacturers().equals(namensx)).collect(Collectors.toList());
                    chon = 6;

                    break;
                case 3:
                    String type = scanner.nextLine();
                    productList = list.stream().filter(item -> item.getType().equals(type)).collect(Collectors.toList());
                    chon = 6;

                    break;
                case 4:
                    int price = scanner.nextInt();
                    productList = list.stream().filter(item -> item.getPrice() == price).collect(Collectors.toList());
                    chon = 6;

                    break;
            }
        }while (chon != 6);
        Log("Danh sach tim kiem: ","yellow","");
        if(productList.size() == 0){
            Log("k co spam","red","");
        }else {
            for (Product p: productList) {
                logItemProduct(p);
            }
        }
        return 6;
    }

    public static int addProduct(Scanner scanner){
        Log("Ban muon them bn san pham: ","yellow","");
        int num = scanner.nextInt();
        String input;
        scanner.nextLine();

        for (int i = 0; i < num; i++) {
            System.out.println("Them san pham " + (i + 1 ));
            Product p = new Product();
            p.setId(list.size() == 0 ? 1 : list.get(list.size()-1).getId()+ 1) ;
            for (int j = 1; j < 5; j++) {
                showProduct(j);
                input = scanner.nextLine();
                if(j == 1 ) p.setNameProduct(input);
                if(j == 2) p.setNameManufacturers(input);
                if(j == 3 ) p.setType(input);
                if(j == 4 ) p.setPrice(Integer.parseInt(input));
            }
            list.add(p);
        }
        return 6;
    }
    public static CheckProduct checkById(Scanner scanner){
        Log("Nhap id : ","yellow","");
        CheckProduct checkProduct = new CheckProduct();
        int id = scanner.nextInt();
        Product p = new Product();
        int index = -1;
        for (int i = 0; i < list.size(); i++) {
            Product p1 = list.get(i);
            if(p1.getId() == id){
                p = p1;
                index = i;
            }
        }
        checkProduct.setIndexProduct(index);
        checkProduct.setProduct(p);
        return checkProduct;
    }
    public static int edit(Scanner scanner){
        CheckProduct checkProduct = checkById(scanner);
        Product p = checkProduct.getProduct();
        int index = checkProduct.getIndexProduct();
        if(index == -1){
            Log("k co spham theo id","red","");
            return 6;
        }
        logItemProduct(p);
        Log("Ban muon sua","yellow","");
        showProduct(0);
        int j;
        do {
            j = scanner.nextInt();
            scanner.nextLine();
            showProduct(j);
            String input = scanner.nextLine();
            if(j == 1 ) p.setNameProduct(input);
            if(j == 2 ) p.setNameManufacturers(input);
            if(j == 3 ) p.setType(input);
            if(j == 4 ) p.setPrice(Integer.parseInt(input));
            if(j == 1 || j == 3 || j == 4 || j == 2 ) j = 6;
        }while (j != 6);
        list.set(index ,p);
        Log("Edit thanh cong","yellow","");

        return  6;
    }
    public static int deleteById(Scanner scanner){
        CheckProduct checkProduct = checkById(scanner);
        int index = checkProduct.getIndexProduct();
        if(index == -1){
            Log("k co spham theo id","red","");
            return 6;
        }
        list.remove(index);
        Log("Xoa thanh cong","red","");
        return 6;
    }




    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int chon ;
        do{
            showMenu("");
            chon = scanner.nextInt();
            clearLog();
            switch (chon){
                case 1:
                    Log("Danh sach san pham: ","yellow","");


                    if(list.size() != 0){
                        for (int i = 0; i < list.size(); i++) {
                            Product p = list.get(i);
                            logItemProduct(p);
                        }
                        chon = 6;
                    }else{
                        Log("k co spam","red","");
                        chon = 6;
                    }
                    break;
                case 2:
                    chon = search(scanner);
                    break;
                case 3:
                    chon = addProduct(scanner);
                    break;
                case 4:
                    chon = edit(scanner);
                    break;
                case 5:
                    chon = deleteById(scanner);
                    break;
            }
        }while (chon != 0);
    }

}