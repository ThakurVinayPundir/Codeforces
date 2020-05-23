/*

Codeforces Round #429 (Div. 1)
problem D. Destiny using java implementation
It is implemented using persistent segment tree.

*/ 


    import java.util.*;
    import java.io.*;
    public class Main{
         static int root[],lc[],rc[],sz[],tot=1;
         public static void main(String []args) throws IOException{
            BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));
            PrintWriter pw=new PrintWriter(System.out);
            String s[]=sc.readLine().trim().split(" ");
            int n=Integer.parseInt(s[0]);
            int q=Integer.parseInt(s[1]);
            root=new int[(int)(7e6)];
            lc=new int[(int)(7e6)];
            rc=new int[(int)(7e6)];
            sz=new int[(int)(7e6)];
            root[0]=0;
            pre(0,1,n);
            StringBuilder sb=new StringBuilder();
            s=sc.readLine().trim().split(" ");
            for(int i=1;i<=n;i++){
                int x=Integer.parseInt(s[i-1]);
                root[i]=tot++;
                //System.out.println(tot);
                update(root[i],root[i-1],1,n,x);
            }
            
            while(q-->0){
                s=sc.readLine().trim().split(" ");
                int l=Integer.parseInt(s[0]);
                int r=Integer.parseInt(s[1]);
                int k=Integer.parseInt(s[2]);
                int len=(r-l+1+k)/k;
                sb.append(query(root[l-1],root[r],1,n,len)+"\n");
            }
            pw.println(sb.toString());
            pw.flush();
            sc.close();
            pw.close();
         }
         public static void update(int curr,int prev,int l,int r,int x){
             sz[curr]=sz[prev]+1;
             if(l>=r)return;
             int val=tot;
             int mid=(l+r)>>1;
             if(x<=mid){
                 lc[curr]=tot++;
                 rc[curr]=rc[prev];
                 update(lc[curr],lc[prev],l,mid,x);
             }else{ 
                 rc[curr]=tot++;
                 lc[curr]=lc[prev];
                 update(rc[curr],rc[prev],mid+1,r,x);
             }
         }
         public static int query(int ql,int qr,int l,int r,int len){
             if(sz[qr]-sz[ql]<len)return -1;
             if(l==r)return l;
             int mid=(l+r)/2;
             int x=query(lc[ql],lc[qr],l,mid,len);
             if(x!=-1)return x;
             return query(rc[ql],rc[qr],mid+1,r,len);
         }
         public static void pre(int curr,int l,int r){
             if(l==r)return;
             lc[curr]=tot++;
             int val=tot;
             int mid=(l+r)>>1;
             pre(lc[curr],l,mid);
             rc[curr]=tot++;
             pre(rc[curr],mid+1,r);
         }
    }