/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playlisthb;

/**
 *
 * @author Wilson
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
public class PlayListHB {

    static class playlist{
        String judul, penyanyi;
        int durasi, count;
        
        public playlist(String judul, String penyanyi, int durasi){
            this.durasi=durasi;
            this.judul=judul;
            this.penyanyi=penyanyi;
        }
        public playlist(){
            
        }
    }
    
    static class song{
        String judul, penyanyi;
        int durasi, count;
        
        public song(String judul, String penyanyi, int durasi, int count){
            this.durasi=durasi;
            this.judul=judul;
            this.penyanyi=penyanyi;
            this.count=count;
        }
        public song(){
            
        }
    }
    
    static Scanner sc=new Scanner(System.in);
    
    static int temp;
    
    static ArrayList<song> songs=new ArrayList<song>();
    
    static ArrayList<playlist> list=new ArrayList<playlist>();
    
    static boolean AddSong(String judul, String penyanyi, int durasi){
        boolean add=false;
        if(judul.equals("")){
            add=false;
        }
        else{
            song newsong=new song(judul, penyanyi, durasi, 0);
            songs.add(newsong);
            add=true;
        }
        return add;
    }
    
    static int Play(int n){
        temp=n;
        Random r=new Random();
        n=r.nextInt(songs.size());
        if(songs.get(n).judul.equals("")){
           n=r.nextInt(songs.size()); 
        }
        songs.get(n).count++;
        System.out.println(songs.get(n).judul+" "+songs.get(n).penyanyi+" Durasi:"+songs.get(n).durasi+" Count:"+songs.get(n).count);
        return n;
    }
    
    static void AddPlaylist(String judul, String penyanyi, int durasi){
        playlist newlist=new playlist(judul, penyanyi, durasi);
        list.add(newlist);
        System.out.println("Playlist berrhasil disimpan!");
    }
    
    static void DeletePlaylist(int n){
        list.remove(n);
        System.out.println("PlayList berhasil dihapus!");
    }
    
    static void PlayAgain(int n){
        songs.get(n).count++;
        System.out.println(songs.get(n).judul+" "+songs.get(n).penyanyi+" Durasi:"+songs.get(n).durasi+" Count:"+songs.get(n).count);
    }
    
    static void PrevSong(){
        songs.get(temp).count++;
        System.out.println(songs.get(temp).judul+" "+songs.get(temp).penyanyi+" Durasi:"+songs.get(temp).durasi+" Count:"+songs.get(temp).count);
    }
    
    public static void main(String[] args) {
        //dummy song
        songs.add(new song("Love Is Gone","Slander",256,0));
        songs.add(new song("Human","Christina Perri",251,0));
        songs.add(new song("Astronaut In The Ocean","Masked Wolf",133,0));
        songs.add(new song("At My Worst","Pink Sweat$",170,0));
        songs.add(new song("It Will Rain","Bruno Mars",258,0));
        
        int menu=0;
        boolean exit=false;
        
        System.out.println("Welcome to PlayList HB");
        while(!exit){
            System.out.println("1. Add Song\n2. Play\n3. Exit");
            menu=sc.nextInt();
            switch(menu){
                case 1:
                    System.out.println("Masukkan judul lagu:");
                    String judul=sc.next();
                    System.out.println("Masukkan nama penyanyi:");
                    String penyanyi=sc.next();
                    System.out.println("Masukkan durasi:(dalam sec)");
                    int durasi=sc.nextInt();
                    boolean addsong=AddSong(judul, penyanyi, durasi);
                    
                    if(addsong){
                        System.out.println("Data berhasil ditambah!");
                    }
                    else{
                        System.out.println("Data gagal ditambah!");
                    }
                    break;
                case 2:
                    int n=0;
                    n=Play(n);
                    int menu2;
                    boolean keluar=false;
                    while(!keluar){
                        System.out.println("1. Add Playlist\n2. Remove Playlist\n3. Play Again\n4. Prev Song\n5. Next Song\n6. Stop");
                        menu2=sc.nextInt();
                        switch(menu2){
                            case 1:
                                judul=songs.get(n).judul;
                                penyanyi=songs.get(n).penyanyi;
                                durasi=songs.get(n).durasi;
                                AddPlaylist(judul, penyanyi, durasi);
                                break;
                            case 2:
                                DeletePlaylist(n);
                                break;
                            case 3:
                                PlayAgain(n);
                                break;
                            case 4:
                                PrevSong();
                                break;
                            case 5:
                                Play(n);
                                break;
                            case 6:
                                int x=songs.get(0).count;
                                int y=songs.get(0).count;
                                int min=0;
                                int max=0;
                                for(int i=1;i<songs.size();i++){
                                    if(x<songs.get(i).count){
                                        x=songs.get(i).count;
                                        max=i;
                                    }
                                    else if(songs.get(i).count>0&&y>songs.get(i).count){
                                        y=songs.get(i).count;
                                        min=i;
                                    }
                                }
                                System.out.println("Lagu dengan play tersedikit: "+songs.get(min).judul+" dan lagu dengan play terbanyak: "+songs.get(max).judul);
                                keluar=true;
                                break;
                        }
                    }
                    break;
                case 3:
                    exit=true;
                    break;
            }
            
        }
    }
    
}
