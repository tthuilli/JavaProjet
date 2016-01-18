package DotsAndBoxes;

import java.util.Scanner;

public 
class Console {

	private void dessineEtat(EtatJeu EtatJeux)
	{

		int p1score=EtatJeux.scoreP(EtatJeux.getP1());
		int p2score=EtatJeux.scoreP(EtatJeux.getP2());


	    if(EtatJeux.fini())
	    {
	    	if(p1score>p2score)
		    	 System.out.println(EtatJeux.getP1Nom()+ " Gagne !");
	    	else
	    	if(p1score<p2score)
	    		System.out.println(EtatJeux.getP2Nom()+ " Gagne !");
	    	else
	    		System.out.println("Score final : égalité !");

	    }
	    else
	    	System.out.println("Score");
	    
	    System.out.println(EtatJeux.getP1Nom()+" "+p1score+" : "+p2score+ " "+EtatJeux.getP2Nom());
		
	    
	    char [][] state=EtatJeux.getEtat();
	    int boxes=EtatJeux.getBoxes();
	    
	    

		for(int j=0;j<2*boxes+1;j++)
	    {
	    	System.out.printf("%2d ",j);
	    	
			for(int i=0;i<2*boxes+1;i++)
		    {
				char c=state[i][j];
				
				if(c==0)c=' ';
				

		    	if(c=='@')
		    	{
			    	if(j%2==0)
				    	c=('-');
			    	else
			    		c=('|');
		    	}
		    	
		    	if(i%2==1)
		    	{
		    		if(j%2==1)
				    	System.out.print("  ");
		    		else
		    			System.out.print(""+c+c);
		    	}
		    	
		    	System.out.print(c);
		    	
		    	if(i%2==1)
		    	{
		    		if(j%2==1)
				    	System.out.print("  ");
		    		else
		    			System.out.print(""+c+c);

		    	}
		    }

		    System.out.println();
	    }
		

		System.out.printf(" ");
		for(int j=0;j<2*boxes+1;j++)
	    {
	    	System.out.printf(" %2d",j);
	    }

		System.out.println();

	}


   public Console(EtatJeu game) 
   {

       Scanner in = new Scanner(System.in);	


	   
	   while(!game.fini())
	   {
		   dessineEtat(game);
		   
		   if(game.TourPremierJoueur())
			   System.out.println(game.getP1()+" à vous de jouer");
		   else
			   System.out.println(game.getP2()+" à vous de jouer");


		   int x=in.nextInt();
		   int y=in.nextInt();

		   System.out.println("======  "+x+"  "+y);
		   
		   
		   game.action(new PointI(x,y));
		   

		   System.out.println("======");
	   
	   }
	   
	   in.close();

   }
   
}

