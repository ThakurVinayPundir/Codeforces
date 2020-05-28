import java.util.*;
import java.io.*;
public class Main{
    static char ch[];
    static Node t[];
    static class Node{
        int ar[];
        public Node(){
            this.ar=new int[26];
        }
    }
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        ch=sc.next().toCharArray();
        t=new Node[ch.length*4+1];
        build(0,0,ch.length-1);
        int n=sc.nextInt();
        for(int i=0;i<n;i++){
            int q=sc.nextInt();
            if(q==1){
                int pos=sc.nextInt()-1;
                int val=sc.next().toCharArray()[0]-'a';
                update(0,0,ch.length-1,pos,val);
                int cnt=0;
            }else{
                int l=sc.nextInt()-1;
                int r=sc.nextInt()-1;
                Node res=query(0,0,ch.length-1,l,r);
                int ans=0;
                for(int j=0;j<26;j++)ans+=(res.ar[j])>0?1:0;
                System.out.println(ans);
            }
        }
    }
    public static void build(int v,int l,int r){
        if(l>r)return;
        if(l==r){
            t[v]=new Node();
            t[v].ar[ch[l]-'a']++;
        }else{
            int mid=(l+r)>>1;
            build(2*v+1,l,mid);
            build(2*v+2,mid+1,r);
            t[v]=combine(t[2*v+1],t[2*v+2]);
        }
    }
    public static Node combine(Node a,Node b){
        Node tmp=new Node();
        for(int i=0;i<26;i++){
            tmp.ar[i]=a.ar[i]+b.ar[i];
        }
        return tmp;
    }
    public static void update(int v,int tl,int tr,int p,int val){
        if(tl>tr)return;
        if(tl==tr){
            t[v].ar[ch[p]-'a']--;
            ch[p]=(char)(val+'a');
            t[v].ar[val]++;
        }else{
            int mid=(tl+tr)>>1;
            if(p<=mid)update(2*v+1,tl,mid,p,val);
            else update(2*v+2,mid+1,tr,p,val);
            t[v]=combine(t[2*v+1],t[2*v+2]);
        }
    }
    public static Node query(int v,int tl,int tr,int l,int r){
        if(tl>tr||tl>r|tr<l)return new Node();
        if(tl>=l&&tr<=r){
            return t[v];
        }int mid=(tl+tr)>>1;
        Node a=query(2*v+1,tl,mid,l,r);
        Node b=query(2*v+2,mid+1,tr,l,r);
        Node tmp=combine(a,b);
        return tmp;
    }
}