package guide;

import java.sql.SQLException;

public class Dijkstra { 
	/**
	 * @author 王超凡
	 * 已知起点终点，求出最短路径,返回int[],表示路径
	 */
		private static final int inf=Integer.MAX_VALUE;
	    public static int[] Dijkstra(double[][] W1, int start, int end) {
	    	double[] dist=new double[W1[0].length];//从起点到各点的距离
	    	int[] path=new int[W1[0].length];//存储当前最短路径前一个点的编号
	    	int[] apath=new int[path.length];//存放从起点到终点路径的走向
	    	int[] s=new int[W1[0].length];//如果已访问该节点，为1，没走过，为0
	    	double minidis;//最短距离
	    	int i,j,u=-1;
	    	for(i=0;i<W1[0].length;i++){//如果与start间有连接，则把start当成前一个点，否则至-1
	    		dist[i]=W1[start][i];
	    		s[i]=0;
	    		if(W1[start][i]<inf)
	    			path[i]=start;
	    		else
	    			path[i]=-1;
	    	}
	    	s[start]=1;
	    	path[start]=0;
	    	for(i=0;i<W1[0].length;i++){
	    		minidis=inf;
	    		for(j=0;j<W1[0].length;j++){
	    			if(s[j]==0&&dist[j]<minidis){
	    				u=j;
	    				minidis=dist[j];
	    			}
	    		}
    			s[u]=1;
    			for(j=0;j<W1[0].length;j++){
    				if(s[j]==0)
    					if(W1[u][j]<inf&&dist[u]+W1[u][j]<dist[j]){
    						dist[j]=dist[u]+W1[u][j];
    						path[j]=u;
    					}
    			}
	    	}
	    	int d=0;//路径中顶点个数
	    	int k;
	    	i=end;
	    	if(s[i]==1&&i!=start){
	    		apath[d]=i;
	    		k=path[i];
	    		if(k!=-1){
	    			while(k!=start){
	    				d++;
	    				apath[d]=k;
	    				k=path[k];
	    			}
	    			d++;
	    			apath[d]=start;
	    			//System.out.print(apath[d]);	
	    		}
	    	}
	    	int[] finalpath=new int[d+1];
	    	for(j=d,i=0;j>=0&&i<=d;j--,i++){
	    		finalpath[i]=apath[j];
				//System.out.print(" "+finalpath[i]);
			}
			return finalpath;  
	        
	    }
	    /*public static void main(String[] args) {  
	        // 建立一个权值矩阵  
	        double[][] W1 = {
	                { 0, 1, 4, inf, inf, inf },  
	                { 1, 0, 2, 7, 5, inf },  
	                { 4, 2, 0, inf, 1,inf },   
	                { inf, 7,inf, 0, 3, 2 },  
	                {inf, 5, 1, 3, 0, 6 },   
	                {inf, inf, inf, 2, 6, 0 } }; 
	        int[] p=Dijkstra(W1,2,5);
	        for(int i=0;i<p.length;i++)
	        	System.out.println(p[i]);
	    }*/  
}
