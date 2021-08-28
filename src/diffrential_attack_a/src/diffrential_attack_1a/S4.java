package diffrential_attack_1a;

public class S4 {
	int index=0;
	int max_bias=0;
	int output[][] = {
			{0,0,0,0 ,0,0,0,0 ,1,1,0,1 ,0,0,0,0 ,0,0,0,0},{0,0,0,1 ,0,0,0,0 ,0,1,1,0 ,0,0,0,0 ,0,0,0,0},
			{0,0,1,0 ,0,0,0,0 ,0,0,0,0 ,0,0,0,0 ,0,0,0,0},{0,0,1,1 ,0,0,0,0 ,1,0,1,0 ,0,0,0,0 ,0,0,0,0},
			{0,1,0,0 ,0,0,0,0 ,1,1,1,0 ,0,0,0,0 ,0,0,0,0},{0,1,0,1 ,0,0,0,0 ,0,0,1,1 ,0,0,0,0 ,0,0,0,0},
			{0,1,1,0 ,0,0,0,0 ,1,0,1,1 ,0,0,0,0 ,0,0,0,0},{0,1,1,1 ,0,0,0,0 ,0,1,0,1 ,0,0,0,0 ,0,0,0,0},
			{1,0,0,0 ,0,0,0,0 ,0,1,1,1 ,0,0,0,0 ,0,0,0,0},{1,0,0,1 ,0,0,0,0 ,0,0,0,1 ,0,0,0,0 ,0,0,0,0},
			{1,0,1,0 ,0,0,0,0 ,1,0,0,1 ,0,0,0,0 ,0,0,0,0},{1,0,1,1 ,0,0,0,0 ,0,1,0,0 ,0,0,0,0 ,0,0,0,0},
			{1,1,0,0 ,0,0,0,0 ,0,0,1,0 ,0,0,0,0 ,0,0,0,0},{1,1,0,1 ,0,0,0,0 ,1,0,0,0 ,0,0,0,0 ,0,0,0,0},
			{1,1,1,0 ,0,0,0,0 ,1,1,0,0 ,0,0,0,0 ,0,0,0,0},{1,1,1,1 ,0,0,0,0 ,1,1,1,1 ,0,0,0,0 ,0,0,0,0}};
	int bin_choose[][] ={{0,0,0,0},{0,0,0,1},{0,0,1,0},{0,0,1,1},
			{0,1,0,0},{0,1,0,1},{0,1,1,0},{0,1,1,1},
			{1,0,0,0},{1,0,0,1},{1,0,1,0},{1,0,1,1},
			{1,1,0,0},{1,1,0,1},{1,1,1,0},{1,1,1,1}};
	int s_box[][]=new int[16][16];
	public int s_box() {
		
		for(int i=0;i<16;i++) {
			for(int x=0;x<16;x++) {
				for(int k=0;k<4;k++) {
					if(bin_choose[i][k]==1) 
						output[x][k+4]=output[x][k]^bin_choose[i][k];
					else
						output[x][k+4]=output[x][k];
					
				}
			}
			
			for(int x=0;x<16;x++) {
				for(int c=4;c<8;c++) {
					switch(c) {
					case 4:
						index=output[x][c]*8;
						break;
					case 5:
						index=index+output[x][c]*4;
						break;
					case 6:
						index=index+output[x][c]*2;
	        			break;
					case 7:
						index=index+output[x][c];
					}
				}
				for(int c=8;c<12;c++)
					output[x][c+4]=output[index][c];
				index=0;
			}
			
			for(int x=0;x<16;x++) {
				for(int k=8;k<12;k++) {
						output[x][k+8]=output[x][k]^output[x][k+4];
				}	
			}
			for(int x=0;x<16;x++) {
				for(int c=16;c<20;c++) {
					switch(c) {
					case 16:
						index=output[x][c]*8;
						break;
					case 17:
						index=index+output[x][c]*4;
						break;
					case 18:
						index=index+output[x][c]*2;
		       			break;
					case 19:
						index=index+output[x][c];
					}
				}
					s_box[i][index]++;
					index=0;
				}
			}
		max_bias=0;
		for(int jj=1;jj<16;jj++) {
			for(int kk=1;kk<16;kk++) 
				if(Math.abs(s_box[jj][kk])>max_bias) {
					max_bias=Math.abs(s_box[jj][kk]);
				}
			}
		return max_bias;
	}
	public int get_s_box(int x,int y) {
		return s_box[x][y];
		
	}
}