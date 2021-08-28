package linear_attack_1b;
import java.lang.Math;


public class Main_Code {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		int max_bias_each_r[]= {0,0,0,0};
		double max_bias=0.0;
		int p=0;
		double top_bias_6[]={0.0,0.0,0.0,0.0,0.0,0.0};
		double min=0;
		int min_index=0;
		int s_box_r1[]= {0,0,0,0,0,0,0,0};
		int s_box_r2[]= {0,0,0,0,0,0,0,0};
		int s_box_r3[]= {0,0,0,0,0,0,0,0};	
		String ss1[]= new String[4];
		String s[]= {"","","","","","","","","","","","","","","",""};
		double bias_r1[]= {-1,-1,-1,-1};//saving bias value of each sbox in round 1
		int XY_bias_r1[]= {-1,-1,-1,-1,-1,-1,-1,-1};//saving of sbox input/output of round 1
		double bias_r2[]= {-1,-1,-1,-1};
		int XY_bias_r2[]= {-1,-1,-1,-1,-1,-1,-1,-1};
		double bias_r3[]= {-1,-1,-1,-1};
		int XY_bias_r3[]= {-1,-1,-1,-1,-1,-1,-1,-1};
		double bias=1; //total bias
		double bias1=1;//bias of round 1
		double bias2=1;
		double bias3=1;
		int pow=0;
		boolean active_s=false;
		String temp="";
		long finish = 0;
		long timeElapsed = 0;
		long finish1 = 0;
		long timeElapsed1 = 0;
		S1 S_Box1=new S1();//sbox calculation
		S2 S_Box2=new S2();
		S3 S_Box3=new S3();
		S4 S_Box4=new S4();
		max_bias=S_Box1.s_box();
        max_bias_each_r[1]=S_Box2.s_box();
		max_bias_each_r[2]=S_Box3.s_box();
		max_bias_each_r[3]=S_Box4.s_box();
		
		for(int i=1;i<4;i++)//max bias calculation of each round for route filtering
			if(max_bias_each_r[i]>max_bias)
				max_bias=max_bias_each_r[i];				
		max_bias=(double)max_bias/16;

		for(int r1=0;r1<16;r1++) {
			finish = System.currentTimeMillis();
			timeElapsed = (finish - start);
			//System.out.println();
			//System.out.println("input of sbox1 round 1>>> " +r1+ "  in : "+timeElapsed/1000+"s");
			finish=0;timeElapsed=0;
			s_box_r1[0]=r1;
			for(int r2=0;r2<16;r2++) {
				s_box_r1[1]=r2;
				for(int r3=0;r3<16;r3++) {
					s_box_r1[2]=r3;
					for(int r4=0;r4<16;r4++) {
						s_box_r1[3]=r4;
						for(int r11=0;r11<16;r11++) {
							s_box_r1[4]=r11;
							for(int r22=0;r22<16;r22++) {
								s_box_r1[5]=r22;
								for(int r33=0;r33<16;r33++) {
									s_box_r1[6]=r33;
									for(int r44=0;r44<16;r44++) {
										s_box_r1[7]=r44;
										
						if(s_box_r1[0]!=0 && s_box_r1[4]!=0 ) {//sbox bias calculation
							bias_r1[0]=(double)S_Box1.get_s_box(s_box_r1[0],s_box_r1[4])/16;
							XY_bias_r1[0]=s_box_r1[0];XY_bias_r1[1]=s_box_r1[4];
							}else if(s_box_r1[0]==0 && s_box_r1[4]!=0 || s_box_r1[0]!=0 && s_box_r1[4]==0  )
								continue;//route filtering
						
						if(s_box_r1[1]!=0 && s_box_r1[5]!=0 ) {
							bias_r1[1]=(double)S_Box2.get_s_box(s_box_r1[1],s_box_r1[5])/16;
							XY_bias_r1[2]=s_box_r1[1];XY_bias_r1[3]=s_box_r1[5];
							}else if(s_box_r1[1]==0 && s_box_r1[5]!=0 || s_box_r1[1]!=0 && s_box_r1[5]==0  )
								continue;
						
						if(s_box_r1[2]!=0 && s_box_r1[6]!=0) {
							bias_r1[2]=(double)S_Box3.get_s_box(s_box_r1[2],s_box_r1[6])/16;
							XY_bias_r1[4]=s_box_r1[2];XY_bias_r1[5]=s_box_r1[6];
							}else if(s_box_r1[2]==0 && s_box_r1[6]!=0 || s_box_r1[2]!=0 && s_box_r1[6]==0  )
								continue;
						
						if(s_box_r1[3]!=0 && s_box_r1[7]!=0) {
							bias_r1[3]=(double)S_Box4.get_s_box(s_box_r1[3],s_box_r1[7])/16;
							XY_bias_r1[6]=s_box_r1[3];XY_bias_r1[7]=s_box_r1[7];
							}else if(s_box_r1[3]==0 && s_box_r1[7]!=0 || s_box_r1[3]!=0 && s_box_r1[7]==0  )
								continue;
		
						if(bias_r1[0]==-1.0 && bias_r1[1]==-1.0 && bias_r1[2]==-1.0 && bias_r1[3]==-1.0) {	
							continue;//route filtering
						}
						
						if(bias_r1[0]==0.0 || bias_r1[1]==0.0 || bias_r1[2]==0.0 || bias_r1[3]==0.0) {
							bias_r1[0]=-1;
							bias_r1[1]=-1;
							bias_r1[2]=-1;
							bias_r1[3]=-1;
							continue;}//route filtering
											
						bias1=1;
						pow=0;
						for(int i=0;i<4;i++) //round bais calculation
							if(bias_r1[i]!=-1) {
								bias1=(double)bias1*bias_r1[i];	
								pow++;
							}

						if(pow>1) 
							bias1=(double)(Math.pow(2, (pow-1)))*bias1;
						
						
						bias=(double)(bias1);
						active_s=false;
							if((Math.abs((bias)*max_bias*max_bias*4)<Math.abs(top_bias_6[min_index])))
								active_s=true;
						if(active_s) {
							bias_r1[0]=-1;
							bias_r1[1]=-1;
							bias_r1[2]=-1;
							bias_r1[3]=-1;
							continue;//route filtering based on line 122
						}
							
						
		//round 2				///////////////////////////////////////////////////////////////////////////////
		
						// round Permutation calculation. till line 177
				for(int k=0;k<4;k++) 
					s_box_r2[k]=s_box_r1[k+4];
				 
				for(int k=0;k<4;k++) {
					ss1[k]=Integer.toBinaryString(s_box_r2[k]);
					if(Integer.toBinaryString(s_box_r2[k]).length()==1)
						ss1[k]="000"+ss1[k];
					if(Integer.toBinaryString(s_box_r2[k]).length()==2)
						ss1[k]="00"+ss1[k];
					if(Integer.toBinaryString(s_box_r2[k]).length()==3)
						ss1[k]="0"+ss1[k];
				}

				
				for(int k=0;k<4;k++)
					s[k]=ss1[0].substring(k, k+1);
				for(int k=0;k<4;k++)
					s[k+4]=ss1[1].substring(k, k+1);
				for(int k=0;k<4;k++)
					s[k+8]=ss1[2].substring(k, k+1);
				for(int k=0;k<4;k++)
					s[k+12]=ss1[3].substring(k, k+1);

				temp="";
					temp=s[4];	s[4]=s[1];	s[1]=temp;	temp=s[8];	s[8]=s[2];
					s[2]=temp;	temp=s[12];	s[12]=s[3];	s[3]=temp;	temp=s[9];
					s[9]=s[6];	s[6]=temp;	temp=s[13];	s[13]=s[7];	s[7]=temp;
					temp=s[14];	s[14]=s[11];s[11]=temp;
					
					for(int k=0;k<4;k++)
						ss1[k]="";
					for(int k=0;k<4;k++)
						ss1[0]+=s[k];
					for(int k=0;k<4;k++)
						ss1[1]+=s[k+4];
					for(int k=0;k<4;k++)
						ss1[2]+=s[k+8];
					for(int k=0;k<4;k++)
						ss1[3]+=s[k+12];

					for(int k=0;k<4;k++)
						s_box_r2[k]=Integer.parseInt(ss1[k], 2);
				////////////////////////////////////////////////////
						for(int r2_1=0;r2_1<16;r2_1++) {
							s_box_r2[4]=r2_1;
							for(int r2_2=0;r2_2<16;r2_2++) {
								s_box_r2[5]=r2_2;
								for(int r2_3=0;r2_3<16;r2_3++) {
									s_box_r2[6]=r2_3;
									for(int r2_4=0;r2_4<16;r2_4++) {
										s_box_r2[7]=r2_4;
				if(s_box_r2[0]!=0 && s_box_r2[4]!=0 ) {
					bias_r2[0]=(double)S_Box1.get_s_box(s_box_r2[0],s_box_r2[4])/16;
					XY_bias_r2[0]=s_box_r2[0];XY_bias_r2[1]=s_box_r2[4];
				}else if(s_box_r2[0]==0 && s_box_r2[4]!=0 || s_box_r2[0]!=0 && s_box_r2[4]==0  )
					continue;
				if(s_box_r2[1]!=0 && s_box_r2[5]!=0 ) {
					bias_r2[1]=(double)S_Box2.get_s_box(s_box_r2[1],s_box_r2[5])/16;
					XY_bias_r2[2]=s_box_r2[1];XY_bias_r2[3]=s_box_r2[5];
				}else if(s_box_r2[1]==0 && s_box_r2[5]!=0 || s_box_r2[1]!=0 && s_box_r2[5]==0  )
					continue;
				if(s_box_r2[2]!=0 && s_box_r2[6]!=0 ) {
					bias_r2[2]=(double)S_Box3.get_s_box(s_box_r2[2],s_box_r2[6])/16;
					XY_bias_r2[4]=s_box_r2[2];XY_bias_r2[5]=s_box_r2[6];
				}else if(s_box_r2[2]==0 && s_box_r2[6]!=0 || s_box_r2[2]!=0 && s_box_r2[6]==0  )
					continue;
				if(s_box_r2[3]!=0 && s_box_r2[7]!=0 ) {
					bias_r2[3]=(double)S_Box4.get_s_box(s_box_r2[3],s_box_r2[7])/16;
					XY_bias_r2[6]=s_box_r2[3];XY_bias_r2[7]=s_box_r2[7];
				}else if(s_box_r2[3]==0 && s_box_r2[7]!=0 || s_box_r2[3]!=0 && s_box_r2[7]==0  )
					continue;

				if(bias_r2[0]==-1.0 && bias_r2[1]==-1.0 && bias_r2[2]==-1.0 && bias_r2[3]==-1.0) {	
					continue;
				}
				
				if(bias_r2[0]==0.0 || bias_r2[1]==0.0 || bias_r2[2]==0.0 || bias_r2[3]==0.0) {
					bias_r2[0]=-1;
					bias_r2[1]=-1;
					bias_r2[2]=-1;
					bias_r2[3]=-1;
					continue;}
															
				bias2=1;
				pow=0;						
				for(int i=0;i<4;i++) {
					if(bias_r2[i]!=-1) {
						bias2=(double)bias2*bias_r2[i];	
						pow++;
					}}
				if(pow>1) 
					bias2=(double)(Math.pow(2, (pow-1)))*bias2;
				bias=(double)(2)*(bias2*bias1);
				
				active_s=false;
					if((Math.abs((bias*max_bias*2))<Math.abs(top_bias_6[min_index])))
						active_s=true;
				if(active_s) {
					bias_r2[0]=-1;
					bias_r2[1]=-1;
					bias_r2[2]=-1;
					bias_r2[3]=-1;
					continue;
					}
					
				
						
					//round 3			//////////////////////////////////////////////////////////////////
					for(int k=0;k<4;k++) 
						s_box_r3[k]=s_box_r2[k+4];
					 
					for(int k=0;k<4;k++) {
						ss1[k]=Integer.toBinaryString(s_box_r3[k]);
						if(Integer.toBinaryString(s_box_r3[k]).length()==1)
							ss1[k]="000"+ss1[k];
						if(Integer.toBinaryString(s_box_r3[k]).length()==2)
							ss1[k]="00"+ss1[k];
						if(Integer.toBinaryString(s_box_r3[k]).length()==3)
							ss1[k]="0"+ss1[k];
					}

					
					for(int k=0;k<4;k++)
						s[k]=ss1[0].substring(k, k+1);
					for(int k=0;k<4;k++)
						s[k+4]=ss1[1].substring(k, k+1);
					for(int k=0;k<4;k++)
						s[k+8]=ss1[2].substring(k, k+1);
					for(int k=0;k<4;k++)
						s[k+12]=ss1[3].substring(k, k+1);

				 temp="";
						temp=s[4];	s[4]=s[1];	s[1]=temp;	temp=s[8];	s[8]=s[2];	
						s[2]=temp;	temp=s[12];	s[12]=s[3];	s[3]=temp;	temp=s[9];
						s[9]=s[6];	s[6]=temp;	temp=s[13];	s[13]=s[7];	s[7]=temp;
						temp=s[14];	s[14]=s[11];	s[11]=temp;
						
						for(int k=0;k<4;k++)
							ss1[k]="";
						for(int k=0;k<4;k++)
							ss1[0]+=s[k];
						for(int k=0;k<4;k++)
							ss1[1]+=s[k+4];
						for(int k=0;k<4;k++)
							ss1[2]+=s[k+8];
						for(int k=0;k<4;k++)
							ss1[3]+=s[k+12];

						for(int k=0;k<4;k++)
							s_box_r3[k]=Integer.parseInt(ss1[k], 2);
						
							for(int r3_1=0;r3_1<16;r3_1++) {
								s_box_r3[4]=r3_1;
								for(int r3_2=0;r3_2<16;r3_2++) {
									s_box_r3[5]=r3_2;
									for(int r3_3=0;r3_3<16;r3_3++) {
										s_box_r3[6]=r3_3;
										for(int r3_4=0;r3_4<16;r3_4++) {
											s_box_r3[7]=r3_4;
					if(s_box_r3[0]!=0 && s_box_r3[4]!=0 ) {
						bias_r3[0]=(double)S_Box1.get_s_box(s_box_r3[0],s_box_r3[4])/16;
						XY_bias_r3[0]=s_box_r3[0];XY_bias_r3[1]=s_box_r3[4];
					}else if(s_box_r3[0]==0 && s_box_r3[4]!=0 || s_box_r3[0]!=0 && s_box_r3[4]==0  )
						continue;
					
					if(s_box_r3[1]!=0 && s_box_r3[5]!=0 ) {
						bias_r3[1]=(double)S_Box2.get_s_box(s_box_r3[1],s_box_r3[5])/16;
						XY_bias_r3[2]=s_box_r3[1];XY_bias_r3[3]=s_box_r3[5];
					}else if(s_box_r3[1]==0 && s_box_r3[5]!=0 || s_box_r3[1]!=0 && s_box_r3[5]==0  )
						continue;
					
					if(s_box_r3[2]!=0 && s_box_r3[6]!=0 ) {
						bias_r3[2]=(double)S_Box3.get_s_box(s_box_r3[2],s_box_r3[6])/16;
						XY_bias_r3[4]=s_box_r3[2];XY_bias_r3[5]=s_box_r3[6];
					}else if(s_box_r3[2]==0 && s_box_r3[6]!=0 || s_box_r3[2]!=0 && s_box_r3[6]==0  )
						continue;
					
					if(s_box_r3[3]!=0 && s_box_r3[7]!=0 ) {
						bias_r3[3]=(double)S_Box4.get_s_box(s_box_r3[3],s_box_r3[7])/16;
						XY_bias_r3[6]=s_box_r3[3];XY_bias_r3[7]=s_box_r3[7];
					}else if(s_box_r3[3]==0 && s_box_r3[7]!=0 || s_box_r3[3]!=0 && s_box_r3[7]==0  )
						continue;

					if(bias_r3[0]==-1.0 && bias_r3[1]==-1.0 && bias_r3[2]==-1.0 && bias_r3[3]==-1.0) {	
						continue;
						}
					
					if(bias_r3[0]==0.0 || bias_r3[1]==0.0 || bias_r3[2]==0.0 || bias_r3[3]==0.0) {
						bias_r3[0]=-1;
						bias_r3[1]=-1;
						bias_r3[2]=-1;
						bias_r3[3]=-1;
						continue;}
					
					pow=0;											
					bias3=1;						
					for(int i=0;i<4;i++) {
						if(bias_r3[i]!=-1) {
							bias3=(double)bias3*bias_r3[i];	
							pow++;
						}}
					
					if(pow>1) 
						bias3=(double)(Math.pow(2, (pow-1)))*bias3;
					
					bias=(double)(4)*(bias2*bias1*bias3);
					active_s=false;
						if(Math.abs(bias)<Math.abs(top_bias_6[min_index]))
							active_s=true;
				
					if(active_s) {
						bias_r3[0]=-1;
						bias_r3[1]=-1;
						bias_r3[2]=-1;
						bias_r3[3]=-1;
						continue;
						}
					
						if(Math.abs(top_bias_6[min_index])<Math.abs(bias)) {//obtain lowest bias of top 6
							min=Math.abs(top_bias_6[0]);
					        min_index = 0;
					        for (int q = 0; q < 6; q++) {
					            if (Math.abs(top_bias_6[q]) <= min ) {
					                min = Math.abs(top_bias_6[q]);
					                min_index = q;
					            }
					       	}
					       p=0;
							for(int t=0; t<4;t++) {//sbox input/output current route registration
								if(bias_r2[t]==-1.0) {
									
									XY_bias_r2[p]=-1;
									p++;
									XY_bias_r2[p]=-1;
									p++;
								}else
									p+=2;
							}
							p=0;
							for(int t=0; t<4;t++) {//sbox input/output current route registration
								
								if(bias_r3[t]==-1.0) {
									
									XY_bias_r3[p]=-1;
									p++;
									XY_bias_r3[p]=-1;
									p++;
								}else
									p+=2;
							}	
							p=0;
							for(int t=0; t<4;t++) {//sbox input/output current route registration
								
								if(bias_r1[t]==-1.0) {
									
									XY_bias_r1[p]=-1;
									p++;
									XY_bias_r1[p]=-1;
									p++;
								}else
									p+=2;
							}
								top_bias_6[min_index]=bias;
								System.out.println("round 1 ----------------------");
								System.out.print("sbox1_X,Y:"+XY_bias_r1[0]+","+XY_bias_r1[1]+">>");System.out.print("bias:"+bias_r1[0]+"  ");
								System.out.print("sbox2_X,Y:"+XY_bias_r1[2]+","+XY_bias_r1[3]+">>");System.out.print("bias:"+bias_r1[1]);
								System.out.println();
								System.out.print("sbox3_X,Y:"+XY_bias_r1[4]+","+XY_bias_r1[5]+">>");System.out.print("bias:"+bias_r1[2]+"  ");
								System.out.print("sbox4_X,Y:"+XY_bias_r1[6]+","+XY_bias_r1[7]+">>");System.out.println("bias:"+bias_r1[3]);
								System.out.println("Piling-Up r1>>"+bias1);
								
								System.out.println("round 2 ----------------------");
								System.out.print("sbox1_X,Y:"+XY_bias_r2[0]+","+XY_bias_r2[1]+">>");System.out.print("bias:"+bias_r2[0]+"  ");
								System.out.print("sbox2_X,Y:"+XY_bias_r2[2]+","+XY_bias_r2[3]+">>");System.out.print("bias:"+bias_r2[1]+"  ");
								System.out.println();
								System.out.print("sbox3_X,Y:"+XY_bias_r2[4]+","+XY_bias_r2[5]+">>");System.out.print("bias:"+bias_r2[2]+"  ");
								System.out.print("sbox4_X,Y:"+XY_bias_r2[6]+","+XY_bias_r2[7]+">>");System.out.println("bias:"+bias_r2[3]+"  ");
								System.out.println("Piling-Up r2>>"+bias2);
								
								System.out.println("round 3 ----------------------");
								System.out.print("sbox1_X,Y:"+XY_bias_r3[0]+","+XY_bias_r3[1]+">>");System.out.print("bias:"+bias_r3[0]+"  ");
								System.out.print("sbox2_X,Y:"+XY_bias_r3[2]+","+XY_bias_r3[3]+">>");System.out.print("bias:"+bias_r3[1]+"  ");
								System.out.println();
								System.out.print("sbox3_X,Y:"+XY_bias_r3[4]+","+XY_bias_r3[5]+">>");System.out.print("bias:"+bias_r3[2]+"  ");
								System.out.print("sbox4_X,Y:"+XY_bias_r3[6]+","+XY_bias_r3[7]+">>");System.out.println("bias:"+bias_r3[3]+"  ");
								System.out.println("Piling-Up r3>>"+bias3);

								finish1 = System.currentTimeMillis();
								timeElapsed1 = (finish1 - start);
								System.out.print("       total bias>>>>>>"+bias+ " in : "+timeElapsed1/1000+"s");
								finish1=0;timeElapsed1=0;
								System.out.println();
								System.out.print("top_bias_6>>");
								for(int t2=0;t2<6;t2++)
									System.out.print(" "+top_bias_6[t2]);
								System.out.println();
								System.out.println("*************************************************************************************************");
					min=Math.abs(top_bias_6[0]);
			        min_index = 0;
			        for (int q = 0; q < 6; q++) {//obtain lowest bias between top 6
			            if (Math.abs(top_bias_6[q]) <= min ) {
			                min =Math.abs (top_bias_6[q]);
			                min_index = q;
			            }
			       	}
							
					}
						for(int i=0;i<4;i++) {//reset round 3 variable
							bias_r3[i]=-1;	
							XY_bias_r3[i]=-1;
							XY_bias_r3[i+4]=-1;
						}
							}}}}
					for(int i=0;i<4;i++) {
						bias_r2[i]=-1;
						XY_bias_r2[i]=-1;
						XY_bias_r2[i+4]=-1;
					}
					}}}}	
				 for(int i=0;i<4;i++) {
						bias_r1[i]=-1;
						XY_bias_r1[i]=-1;
						XY_bias_r1[i+4]=-1;
				 }
	}}}}}}}}
		finish = System.currentTimeMillis();
		timeElapsed = (finish - start);
		System.out.println();
		System.out.println("total time :"+timeElapsed/1000+"s");
	}
}
