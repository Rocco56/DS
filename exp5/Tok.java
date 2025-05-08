import java.io.*;
import java.util.*;
class Tok{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter no. of nodes: ");
        int n = sc.nextInt();
        int m = n-1;
        int ch =0;
        int token = 0;
        int flag = 0;
        for(int i = 0;i<n;i++){
            System.out.println(" "+i);
        }
        System.out.println(" "+0);
        do {
            System.out.println("Enter Sender: ");
            int s = sc.nextInt();
            System.out.println("Enter Reciever: ");
            int r = sc.nextInt();
            System.out.println("Enter data: ");
            int a = sc.nextInt();
            System.out.println("Token is processing");
            for(int i = token,j=token;(i%n)!=s;i++,j=(j+1)%n){
                System.out.println(" "+j+"->");
            }
            System.out.println(" "+s);
            System.out.println("Sender "+s+"is sending data "+a);
            for(int i = s+1;i!=r;i=(i+1%n)){
                System.out.println("data "+a+" forwarded by process "+i);
            }
            System.out.println("Reciever "+r+" recived data "+a+"\n");
            token= s;
            do {
                try{
                    if(flag==1){
                        System.out.println("Invalid Input");
                    }
                    System.out.println("Do you want to continue?1 for yes,0 for no: ");
                    ch = sc.nextInt();
                    if(ch!=0&&ch!=1){
                        flag=1;
                    
                    }else{
                        flag = 0;
                    }
                }catch(InputMismatchException e){
                    System.out.println("Invalid Input");
                }
                
            } while (ch!=1&&ch!=0);
        } while (ch==1);
    }
}