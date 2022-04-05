package othertasks;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IslandsProblem {
	private static final Logger logg = LogManager.getLogger(IslandsProblem.class);
	static int[][] matrix = new int[100][100];

	public static void solve(int i,int j,int n,int m,int c) {
				matrix[i][j]=c;
				if(i<n && i>=0 && (j+1)<m && j>=0 && matrix[i][j+1]==1) {
					solve(i,j+1,n,m,c);}
					if((i+1)<n && i>=0 && j<m && j>=0 && matrix[i+1][j]==1) {
					solve(i+1,j,n,m,c);}
					if((i-1)<n && (i-1)>=0 && j<m && j>=0 && matrix[i-1][j]==1) {
					solve(i-1,j,n,m,c);}
					if(i<n && i>=0 && j<m && (j-1)>=0 && matrix[i][j-1]==1) {
					solve(i,j-1,n,m,c);}
			
		
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		logg.info("enter value for row:");
		int n = sc.nextInt();
		logg.info("enter value for column");
		int m = sc.nextInt();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				matrix[i][j] = sc.nextInt();
			}
		}
		int ans=0;
		int c=1;
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(matrix[i][j]==1) {
					matrix[i][j]=c;
					ans+=1;
					
					if(i<n && i>=0 && (j+1)<m && j>=0 && matrix[i][j+1]==1) {
					solve(i,j+1,n,m,c);}
					if((i+1)<n && i>=0 && j<m && j>=0 && matrix[i+1][j]==1) {
					solve(i+1,j,n,m,c);}
					if((i-1)<n && (i-1)>=0 && j<m && j>=0 && matrix[i-1][j]==1) {
					solve(i-1,j,n,m,c);}
					if(i<n && i>=0 && j<m && (j-1)>=0 && matrix[i][j-1]==1) {
					solve(i,j-1,n,m,c);}
					
					c+=1;
				}
			}
		}
		logg.info(ans);
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				System.out.print(matrix[i][j]+"  ");	
				
			}
			System.out.println();
		}

	}

}
