/*



Xenia the beginner programmer has a sequence a, consisting of 2 n non-negative integers: a 1,?a 2,?...,?a 2 n. Xenia is currently studying bit operations. To better understand how they work, Xenia decided to calculate some value v for a.

Namely, it takes several iterations to calculate value v. At the first iteration, Xenia writes a new sequence a 1 or a 2,?a 3 or a 4,?...,?a 2 n?-?1 or a 2 n, consisting of 2 n?-?1 elements. In other words, she writes down the bit-wise OR of adjacent elements of sequence a. At the second iteration, Xenia writes the bitwise exclusive OR of adjacent elements of the sequence obtained after the first iteration. At the third iteration Xenia writes the bitwise OR of the adjacent elements of the sequence obtained after the second iteration. And so on; the operations of bitwise exclusive OR and bitwise OR alternate. In the end, she obtains a sequence consisting of one element, and that element is v.

Let's consider an example. Suppose that sequence a?=?(1,?2,?3,?4). Then let's write down all the transformations (1,?2,?3,?4) ??? (1 or 2?=?3,?3 or 4?=?7) ??? (3 xor 7?=?4). The result is v?=?4.

You are given Xenia's initial sequence. But to calculate value v for a given sequence would be too easy, so you are given additional m queries. Each query is a pair of integers p,?b. Query p,?b means that you need to perform the assignment a p?=?b. After each query, you need to print the new value v for the new sequence a.
Input

The first line contains two integers n and m (1?=?n?=?17,?1?=?m?=?105). The next line contains 2 n integers a 1,?a 2,?...,?a 2 n (0?=?a i?<?230). Each of the next m lines contains queries. The i-th line contains integers p i,?b i (1?=?p i?=?2 n,?0?=?b i?<?230) — the i-th query.
Output

Print m integers — the i-th integer denotes value v for sequence a after the i-th query.
Examples
Input
Copy

2 4
1 6 3 5
1 4
3 4
1 2
1 2

Output
Copy

1
3
3
3

*/


import java.util.*;
import java.io.*;
public class Main{
    static int t[],a[];
    public static void main(String args[]) throws IOException{
        BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw=new PrintWriter(System.out);
        String s[]=sc.readLine().trim().split(" ");
        int n=Integer.parseInt(s[0]);
        int m=Integer.parseInt(s[1]);
        t=new int[(int)Math.pow(2,n)*4+2];
        a=new int[(int)Math.pow(2,n)];
        String s1[]=sc.readLine().trim().split(" ");
        for(int i=0;i<Math.pow(2,n);i++)a[i]=Integer.parseInt(s1[i]);
        if(n%2==0)
        build(0,0,(int)Math.pow(2,n)-1,1);
        else build(0,0,(int)Math.pow(2,n)-1,0);
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<m;i++){
            String s2[]=sc.readLine().trim().split(" ");
            int p=Integer.parseInt(s2[0])-1;
            int d=Integer.parseInt(s2[1]);
            if(n%2==0)
            update(0,0,(int)Math.pow(2,n)-1,p,d,1);
            else update(0,0,(int)Math.pow(2,n)-1,p,d,0);
            
            sb.append(t[0]+"\n");
        }
        pw.println(sb.toString().trim());
        pw.flush();
        pw.close();
        sc.close();
    }
    public static void build(int v,int l,int r,int p){
        if(l>r)return ;
        if(l==r){
            t[v]=a[l];
            return;
        }else{
            int mid=(l+r)>>1;
            build(2*v+1,l,mid,p^1);
            build(2*v+2,mid+1,r,p^1);
            if(p==1)t[v]=t[2*v+1]^t[2*v+2];
            else t[v]=t[2*v+1]|t[2*v+2];
        }
    }
    public static void update(int v,int tl,int tr,int p,int d,int p1){
        if(tl>tr)return ;
        if(tl==tr){
            t[v]=d;
            return;
        }
        int mid=(tl+tr)>>1;
        if(p<=mid)update(2*v+1,tl,mid,p,d,p1^1);
        else update(2*v+2,mid+1,tr,p,d,p1^1);
        if(p1==1)t[v]=t[2*v+1]^t[2*v+2];
        else t[v]=t[2*v+1]|t[2*v+2];
    }
}