package linear_attack_1a;

public class S4 {
	int max_bias=0;
	int inc = 0;
	int output[][] = new int [16][2];
	int bin_choose[][] ={{0,0,0,0},{0,0,0,1},{0,0,1,0},{0,0,1,1},
			{0,1,0,0},{0,1,0,1},{0,1,1,0},{0,1,1,1},
			{1,0,0,0},{1,0,0,1},{1,0,1,0},{1,0,1,1},
			{1,1,0,0},{1,1,0,1},{1,1,1,0},{1,1,1,1}};
	int bin_x[][] ={{0,0,0,0},{0,0,0,1},{0,0,1,0},{0,0,1,1},
			{0,1,0,0},{0,1,0,1},{0,1,1,0},{0,1,1,1},
			{1,0,0,0},{1,0,0,1},{1,0,1,0},{1,0,1,1},
			{1,1,0,0},{1,1,0,1},{1,1,1,0},{1,1,1,1}};
	int bin_s1_y[][] ={{1,1,0,1},{0,1,1,0},{0,0,0,0},{1,0,1,0},
			{1,1,1,0},{0,0,1,1},{1,0,1,1},{0,1,0,1},
			{0,1,1,1},{0,0,0,1},{1,0,0,1},{0,1,0,0},
			{0,0,1,0},{1,0,0,0},{1,1,0,0},{1,1,1,1}};
	int s_box[][]=new int[16][16];
	int xor=0;
	public int s_box() {
		
		for(int i=0;i<16;i++) {
			for(int x=0;x<16;x++) {
				for(int k=0;k<4;k++) {
					if(bin_choose[i][k]==1) {
						xor=bin_x[x][k]^xor;
					}
				}
				output[x][0]=xor;
				xor=0;
			}
		
			for(int j=0;j<16;j++) {
				for(int y=0;y<16;y++) {
					for(int k=0;k<4;k++) {
						if(bin_choose[j][k]==1) {
							xor=bin_s1_y[y][k]^xor;
						}
					}	
					output[y][1]=xor;
					xor=0;
				}
				for(int s=0;s<16;s++) {
					if(output[s][0]==output[s][1]) {
						s_box[i][j]=++inc;
					}
				}inc=0;
			}
		
		}
		max_bias=0;
		for(int jj=1;jj<16;jj++) {
			for(int kk=1;kk<16;kk++) 
				if(Math.abs(s_box[jj][kk])>max_bias) {
					max_bias=Math.abs(s_box[jj][kk]);
				}
			}
		return max_bias-8;
	}
	public int get_s_box(int x,int y) {
		return s_box[x][y]-8;
		
	}
}
