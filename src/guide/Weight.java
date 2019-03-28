package guide;

import java.sql.SQLException;

public class Weight {
	/**
	 * @author 王超凡
	 * 存放地图数据
	 */
	private static final int inf=Integer.MAX_VALUE;
	public double[][] Weight(){
		double [][] weight=new double[57][57];
		double[][]distance = null;
		Location[] location=new Location[57];
	       for(int i=0;i<57;i++)
	    	   for(int j=0;j<57;j++){
	    		   if(i==j)
	    			   weight[i][j]=0;
	    		   else weight[i][j]=inf;
	    	   }
	       try{
	    		SelectXY xy=new SelectXY();
		    	distance=xy.getxy();
	    	}catch(SQLException sqe){
	    		 sqe.printStackTrace();  
	    	}
	    	for(int i=0;i<distance.length;i++){
	    		location[i]=new Location(distance[i][0],distance[i][1]);
	    	}
	       weight[0][42]=Distance(location[0],location[42]);
	       weight[6][42]=Distance(location[6],location[42]);
	       weight[3][6]=Distance(location[3],location[6]);
	       weight[3][5]=Distance(location[3],location[5]);
	       weight[4][5]=Distance(location[4],location[5]);
	       weight[4][43]=Distance(location[4],location[43]);
	       weight[8][43]=Distance(location[8],location[43]);
	       weight[8][44]=Distance(location[8],location[44]);
	       weight[9][44]=Distance(location[9],location[44]);
	       weight[8][9]=Distance(location[8],location[9]);
	       weight[7][44]=Distance(location[7],location[44]);
	       weight[9][45]=Distance(location[9],location[45]);
	       weight[10][45]=Distance(location[10],location[45]);
	       weight[10][46]=Distance(location[10],location[46]);
	       weight[32][46]=Distance(location[32],location[46]);
	       weight[26][32]=Distance(location[26],location[32]);
	       weight[17][26]=Distance(location[17],location[26]);
	       weight[17][47]=Distance(location[17],location[47]);
	       weight[13][47]=Distance(location[13],location[47]);
	       weight[13][16]=Distance(location[13],location[16]);
	       weight[12][16]=Distance(location[12],location[16]);
	       weight[11][12]=Distance(location[11],location[12]);
	       weight[14][16]=Distance(location[14],location[16]);
	       weight[14][15]=Distance(location[14],location[15]);
	       weight[2][15]=Distance(location[2],location[15]);
	       weight[2][6]=Distance(location[2],location[6]);
	       weight[2][18]=Distance(location[2],location[18]);
	       weight[18][19]=Distance(location[18],location[19]);
	       weight[1][19]=Distance(location[1],location[19]);
	       weight[1][48]=Distance(location[1],location[48]);
	       weight[22][48]=Distance(location[22],location[48]);
	       weight[14][23]=Distance(location[14],location[23]);
	       weight[23][49]=Distance(location[23],location[49]);
	       weight[21][49]=Distance(location[21],location[49]);
	       weight[20][21]=Distance(location[20],location[21]);
	       weight[20][28]=Distance(location[20],location[28]);
	       weight[20][27]=Distance(location[20],location[27]);
	       weight[24][27]=Distance(location[24],location[27]);
	       weight[23][24]=Distance(location[23],location[24]);
	       weight[23][50]=Distance(location[23],location[50]);
	       weight[17][50]=Distance(location[17],location[50]);
	       weight[25][27]=Distance(location[25],location[27]);
	       weight[27][52]=Distance(location[27],location[52]);
	       weight[26][52]=Distance(location[26],location[52]);
	       weight[31][52]=Distance(location[31],location[52]);
	       weight[30][31]=Distance(location[30],location[31]);
	       weight[31][35]=Distance(location[31],location[35]);
	       weight[34][35]=Distance(location[34],location[35]);
	       weight[34][53]=Distance(location[34],location[53]);
	       weight[33][53]=Distance(location[33],location[53]);
	       weight[33][51]=Distance(location[33],location[51]);
	       weight[36][51]=Distance(location[36],location[51]);
	       weight[41][46]=Distance(location[41],location[46]);
	       weight[40][41]=Distance(location[40],location[41]);
	       weight[53][54]=Distance(location[53],location[54]);
	       weight[37][54]=Distance(location[37],location[54]);
	       weight[37][38]=Distance(location[37],location[38]);
	       weight[38][39]=Distance(location[38],location[39]);
	       weight[35][39]=Distance(location[35],location[39]);
	       weight[28][55]=Distance(location[28],location[55]);
	       weight[55][56]=Distance(location[55],location[56]);
	       weight[29][56]=Distance(location[29],location[56]);
	       weight[15][23]=Distance(location[15],location[23]);
	       for(int i=0;i<56;i++){
	    	   for(int j=i+1;j<57;j++){
	    		   if(weight[i][j]!=-1)
	    			   weight[j][i]=weight[i][j];
	    	   }	   
	       }
	       
		return weight;
		
	}

	private double Distance(Location a, Location b) {
		// TODO Auto-generated method stub
		return Math.sqrt(Math.pow(Math.abs(a.x-b.x),2)+Math.pow(Math.abs(a.y-b.y),2));	
	}
}
