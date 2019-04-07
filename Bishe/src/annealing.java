import java.util.Random;

public class annealing {
	public String[][] annealing(String[] moves,double[] xs,double[] ys,double[] xe,double[] ye,String[][] blocks,int count){
		String[][] blocks2=new String[count][]; //count=blocks.length=1043/////0-1042
		//System.out.println(count+" "+blocks.length);
		int layer=0;
		int[] nm=new int[30];
		int n=0;
		int save=0;
		int lines=0;
		String[] newblocks=new String[9999999];
		//System.out.println(blocks[1042][0]);
		
		for(int i=0;i<count;i++){
			if(blocks[i][0].contains("Z")){
				layer++;
				nm[n]=i;
				n++;
				//System.out.println(i+"  "+blocks[i][0]);
			}
		}//there are 23 layers
		int[] startnum=new int[layer];
		int[] endnum=new int [layer];
		for(int i=0;i<startnum.length;i++){
			startnum[i]=nm[i];
			
			//System.out.println(i+"   "+startnum[i]);
			
		} 
		
		for(int i=0;i<endnum.length;i++){
			if(i==startnum.length-1){
				endnum[i]=count-1;
			}
			else{
				endnum[i]=startnum[i+1]-1;
			}
			//System.out.println(i+"   "+endnum[i]);
		}
		
		annealing annl=new annealing();
		
		int blocklength=0;
		for(int i=0;i<layer;i++){
			System.out.println("layer:yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy "+i+" "+blocks[startnum[i]][0]);
			n=0;
			String[][] blocks3=annl.annlayer(startnum[i], endnum[i], blocks, count, xs, ys, xe, ye);
			int lines2=0;
			for(int k=startnum[i];k<endnum[i]+1;k++){
				blocks2[k]=new String[blocks3[n].length];
				lines2=0;
				//System.out.println("blocklinesbbbbbbbbbbbbbb: "+k);
				for(int j=0;j<blocks3[n].length;j++){
					blocks2[k][j]=blocks3[n][j];
					lines++;
					lines2++;
				}
				//System.out.println("lineeeeeeeeeeeeeeeeeeeeee: "+lines2+" block length: "+blocks3[n].length);
				n++;
				
			}
			blocklength=blocklength+blocks3.length;
			
		}
		//System.out.println("blocks::::::::::::::: "+blocks2.length);
		//System.out.println("lines::::::::::::::::::::::"+lines);
		//System.out.println("blocklinesbbbbbbbbbbbbbb: "+blocklength);
		
		
		return blocks2;
		
	}
	
	
	public String[][] annlayer(int startnum,int endnum,String[][] blocks,int count,double[] xs,double[] ys,double[] xe,double[] ye){
		exchange d=new exchange();
		Random r=new Random();
		System.out.println("starttttttt: "+startnum+" enddddddd: "+endnum);
		String[][] blocks2=new String[endnum-startnum+1][];
		double dis=0;
		int save=0;
		int stn=startnum;
		int lines=0;
		
		for(int i=0;i<endnum-startnum+1;i++){
			blocks2[i]=new String[blocks[stn].length];
			for(int j=0;j<blocks[stn].length;j++){
				blocks2[i][j]=blocks[stn][j];
				lines++;
			}
			stn++;
		}
		
		stn=startnum;
		double[] xss=new double[endnum-startnum];
		for(int i=0;i<xss.length;i++){
			xss[i]=xs[stn];
			stn++;
		}
		stn=startnum;
		double[] yss=new double[endnum-startnum];
		for(int i=0;i<yss.length;i++){
			yss[i]=ys[stn];
			stn++;
		}
		stn=startnum;
		double[] yee=new double[endnum-startnum];
		for(int i=0;i<yee.length;i++){
			yee[i]=ys[stn];
			stn++;
		}
		stn=startnum;
		double[] xee=new double[endnum-startnum];
		for(int i=0;i<xee.length;i++){
			xee[i]=ys[stn];
			stn++;
		}
		/*
		for(int i=0;i<endnum-startnum-1;i++){
			//double distance11=Math.sqrt(Math.abs((xee[i-1] - xss[i])* (xee[i-1] - xss[i])+(yee[i-1] - yss[i])* (yee[i-1] - yss[i])));
			double distance12=Math.sqrt(Math.abs((xee[i]- xss[i+1])* (xee[i] - xss[i+1])+(yee[i] - yss[i+1])* (yee[i] - yss[i+1])));
			double distance1=distance12;
			//System.out.println("temp=========="+distance1);
			dis=dis+distance1;
		}
		*/
		
		//double olddis=dis;
		//System.out.println("dis=========="+dis+"startnum======="+startnum);
		for(int loop=0;loop<100;loop++){
			int r1=r.nextInt(endnum-startnum-2)+1;
			int r2=r.nextInt(endnum-startnum-2)+1;
			
			while(r1==r2){
				r1=r.nextInt(endnum-startnum-2)+1;
				r2=r.nextInt(endnum-startnum-2)+1;
			}
			//System.out.println("r1: "+r1+" r2 "+r2);
			
			
			double xstemp1=xss[r1];
			double xetemp1=xee[r1];
			double ystemp1=yss[r1];
			double yetemp1=yee[r1];
			double xstemp2=xss[r2];
			double xetemp2=xee[r2];
			double ystemp2=yss[r2];
			double yetemp2=yee[r2];
			
			/*
			xss[r1]=xstemp2;
			yss[r1]=ystemp2;
			xee[r1]=xetemp2;
			yee[r1]=yetemp2;
			xss[r2]=xstemp1;
			yss[r2]=ystemp1;
			xee[r2]=xetemp1;
			yee[r2]=yetemp1;
			*/
			dis=0;
			double distance11=Math.sqrt(Math.abs((xee[r1-1] - xss[r1])* (xee[r1-1] - xss[r1])+(yee[r1-1] - yss[r1])* (yee[r1-1] - yss[r1])));
			double distance12=Math.sqrt(Math.abs((xee[r1] - xss[r1+1])* (xee[r1] - xss[r1+1])+(yee[r1] - yss[r1+1])* (yee[r1] - yss[r1+1])));
			double distance13=Math.sqrt(Math.abs((xee[r2-1] - xss[r2])* (xee[r2-1] - xss[r2])+(yee[r2-1] - yss[r2])* (yee[r2-1] - yss[r2])));
			double distance14=Math.sqrt(Math.abs((xee[r2] - xss[r2+1])* (xee[r2] - xss[r2+1])+(yee[r2] - yss[r2+1])* (yee[r2] - yss[r2+1])));
			dis=distance11+distance12+distance13+distance14;
			
			double dis2=0;
			
			/*
			for(int i=0;i<endnum-startnum-1;i++){
				//double distance21=Math.sqrt(Math.abs((xee[i-1] - xss[i])* (xee[i-1] - xss[i])+(yee[i-1] - yss[i])* (yee[i-1] - yss[i])));
				double distance22=Math.sqrt(Math.abs((xee[i]- xss[i+1])* (xee[i] - xss[i+1])+(yee[i] - yss[i+1])* (yee[i] - yss[i+1])));
				double distance2=distance22;
				//System.out.println("temp=========="+distance1);
				dis2=dis2+distance2;
			}
			*/
			//System.out.println(dis+"  "+dis2);
			double distance21=Math.sqrt(Math.abs((xee[r1-1] - xss[r2])* (xee[r1-1] - xss[r2])+(yee[r1-1] - yss[r2])* (yee[r1-1] - yss[r2])));
			double distance22=Math.sqrt(Math.abs((xee[r2] - xss[r1+1])* (xee[r2] - xss[r1+1])+(yee[r2] - yss[r1+1])* (yee[r2] - yss[r1+1])));
			double distance23=Math.sqrt(Math.abs((xee[r2-1] - xss[r1])* (xee[r2-1] - xss[r1])+(yee[r2-1] - yss[r1])* (yee[r2-1] - yss[r1])));
			double distance24=Math.sqrt(Math.abs((xee[r1] - xss[r2+1])* (xee[r1] - xss[r2+1])+(yee[r1] - yss[r2+1])* (yee[r1] - yss[r2+1])));
			dis2=distance21+distance22+distance23+distance24;
			
			
			//int iff=1;
			if(dis2<dis){
			
				//dis=dis2;
				save++;
				//System.out.println(blocks2[r1][0]);
				//System.out.println(blocks2[r2][0]);
				//System.out.println(xss[r1]);
				//System.out.println(xss[r2]);
				System.out.println("old dis: "+dis+" new dis: "+dis2);
				
				String[] temp=new String[blocks2[r1].length];
				for(int i=0;i<blocks2[r1].length;i++){
					temp[i]=blocks2[r1][i];
				}
				
				blocks2[r1]=new String[blocks2[r2].length];
				for(int j=0;j<blocks2[r2].length;j++){
					blocks2[r1][j]=blocks2[r2][j];
					//System.out.println(blocks2[r1][j]);
				}
				
				blocks2[r2]=new String[temp.length];
				for(int j=0;j<temp.length;j++){
					blocks2[r2][j]=temp[j];
					//System.out.println(blocks2[r2][j]);
				}
				//System.out.println(blocks2[r1][0]);
				//System.out.println(blocks2[r2][0]);
				//System.out.println(xss[r1]);
				//System.out.println(xss[r2]);
				xss[r1]=xstemp2;
				yss[r1]=ystemp2;
				xee[r1]=xetemp2;
				yee[r1]=yetemp2;
				xss[r2]=xstemp1;
				yss[r2]=ystemp1;
				xee[r2]=xetemp1;
				yee[r2]=yetemp1;
				double distance31=Math.sqrt(Math.abs((xee[r1-1] - xss[r1])* (xee[r1-1] - xss[r1])+(yee[r1-1] - yss[r1])* (yee[r1-1] - yss[r1])));
				double distance32=Math.sqrt(Math.abs((xee[r1] - xss[r1+1])* (xee[r1] - xss[r1+1])+(yee[r1] - yss[r1+1])* (yee[r1] - yss[r1+1])));
				double distance33=Math.sqrt(Math.abs((xee[r2-1] - xss[r2])* (xee[r2-1] - xss[r2])+(yee[r2-1] - yss[r2])* (yee[r2-1] - yss[r2])));
				double distance34=Math.sqrt(Math.abs((xee[r2] - xss[r2+1])* (xee[r2] - xss[r2+1])+(yee[r2] - yss[r2+1])* (yee[r2] - yss[r2+1])));
				double dis3=distance31+distance33+distance32+distance34;
				System.out.println("qeqwewqewq "+dis3);
				
			}
			
			else{
				System.out.println("no save: "+dis+"  "+dis2);
				//System.out.println("qwe");
				/*
				xss[r1]=xstemp1;
				yss[r1]=ystemp1;
				xee[r1]=xetemp1;
				yee[r1]=yetemp1;
				xss[r2]=xstemp2;
				yss[r2]=ystemp2;
				xee[r2]=xetemp2;
				yee[r2]=yetemp2;
				*/
				
			}
			
			
		}
		int llll=0;
		for(int i=0;i<blocks2.length;i++){
			for(int j=0;j<blocks2[i].length;j++){
				llll++;
			}
		}
		
		System.out.println("saveeeeeeee: "+save);
		//System.out.println("old dis: "+" new dis: "+dis);
		//System.out.println("blocks::::::::::::::: "+blocks2.length);
		System.out.println("linessssssssssssssssssssssssssss: "+lines+" lllllll "+llll);
		
		return blocks2;
		
	}

}