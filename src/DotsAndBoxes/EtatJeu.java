package DotsAndBoxes;

import java.util.ArrayList;

public 
class EtatJeu
{
	PointI[] voisins= new PointI[4];
	
	char[][] etat;
	int boxes;
	int dots;
	boolean TourPremierJoueur;
	int difficulte;
	String P1nom,P2nom;
	
	void reset()
	{
		TourPremierJoueur=true;

        for(int j=0;j<2*boxes+1;j++)
            for(int i=0;i<2*boxes+1;i++)
            {
            	if((i%2==0)&&(j%2==0))
                	etat[i][j]='*';
            	else
            		etat[i][j]=0;
            }
        
        
        
        int linecount=2*(boxes*boxes+1);
        
        
        
        for(int k=0;k<dots;k++)
        {
        	int pos=(int)(Math.random()*linecount);
        	

            for(int j=0;(j<2*boxes+1)&&(pos>=0);j++)
                for(int i=0;(i<2*boxes+1)&&(pos>=0);i++)
                {
                	if(((i%2)!=(j%2))&&(etat[i][j]==0))
                	{
                		if(pos==0)
                		{
                			etat[i][j]='.';
                		}
                		pos--;
                	}
                }
        	
        	
        	
        	linecount--;
        }
        
        
	}
	
	
	public EtatJeu(int boxes,int dots,String P1,String P2,int difficulte)
	{		
		voisins[0]=new PointI(-1,0);
		voisins[1]=new PointI(+1,0);
		voisins[2]=new PointI(0,-1);
		voisins[3]=new PointI(0,+1);
		
		this.dots=dots;
        this.etat=new char[2*boxes+1][2*boxes+1];
        this.difficulte=difficulte;
        this.boxes=boxes;
        this.TourPremierJoueur=true;
        
        if(difficulte>0)
        	P2="#Ordinateur";
        
        this.P1nom=P1;
        this.P2nom=P2;

		reset();
	}

	
	int scoreP(char p)
	{
		int score=0;

        for(int j=0;j<boxes;j++)
            for(int i=0;i<boxes;i++)
            {
            	if(etat[2*i+1][2*j+1]==p)score++;
            }

		return score;
	}
	

	String getP1Nom()
	{
		return P1nom;
	}
	
	String getP2Nom()
	{
		return P2nom;
	}
	
	char getP1()
	{
		return P1nom.charAt(0);
	}
	
	char getP2()
	{
		return P2nom.charAt(0);
	}
	
	char[][] getEtat()
	{
		return etat;
	}
	
	int getBoxes()
	{
		return boxes;
	}
	

	boolean TourPremierJoueur()
	{
		return TourPremierJoueur;
	}


    

    private int CompteBorders(PointI pos)
    	{
    		int count=0;

    	    for(int k=0;k<4;k++)
    	    {
    	    	if(etat[pos.x+voisins[k].x][pos.y+voisins[k].y]=='@')count++;
    	    }
    		
    		return count;
    	}

    private int CompteBordersLibres(PointI pos)
    	{
    		int count=0;

    	    for(int k=0;k<4;k++)
    	    {
    	    	if(etat[pos.x+voisins[k].x][pos.y+voisins[k].y]==0)count++;
    	    	if(etat[pos.x+voisins[k].x][pos.y+voisins[k].y]=='.')count++;
    	    }
    		
    		return count;
    	}
    	
    	
    	
	private boolean CheckFerme(PointI pos)
	{
		return 4==CompteBorders(pos);
	}
	
	private boolean updateClosed(PointI pos,char Lettre)
	{
		boolean Ferme= CheckFerme(pos);
		
		if(Ferme)
		etat[pos.x][pos.y]=Lettre;

		return Ferme;
	}
	
	private boolean AjouterLigne(PointI clickpos)
	{
		boolean Ferme=false;

		if(etat[clickpos.x][clickpos.y]=='.')
			etat[clickpos.x][clickpos.y]=0;
		else
			etat[clickpos.x][clickpos.y]='@';
		

		char Lettre;
		if(TourPremierJoueur)
			Lettre=getP1();
		else
			Lettre=getP2();

		
		if((clickpos.x%2)!=0)//ligne horizontale
		{
			if(clickpos.y>0)//fermé?
			{
				if(updateClosed(new PointI(clickpos.x,clickpos.y-1),Lettre))Ferme=true;
			}	
			if(clickpos.y<2*boxes)//fermé en bas?
			{
				if(updateClosed(new PointI(clickpos.x,clickpos.y+1),Lettre))Ferme=true;
			}	
		}

		if((clickpos.y%2)!=0)//ligne
		{
			if(clickpos.x>0)//fermé à gauche?
			{
				if(updateClosed(new PointI(clickpos.x-1,clickpos.y),Lettre))Ferme=true;
			}	
			if(clickpos.x<2*boxes)//fermé à droite?
			{
				if(updateClosed(new PointI(clickpos.x+1,clickpos.y),Lettre))Ferme=true;
			}	
		}
		
		return Ferme;
	}
	

	private PointI getClickAI()
	{
		PointI res=null;
		
		if(difficulte>1)
			res=getClickAIClose();
		
		if(res!=null)return res;///a trouvé un mouvement qui ferme, cool
		
		
		if((res==null)&&(difficulte==3))
			res=getClickAINoLetClose();

		if(res!=null)return res;///trouve qui ne permet pas de fermer

		
		return getClickAIRandom();
	}

	
	private PointI getClickAINoLetClose()
	{
		ArrayList<PointI> lst=new ArrayList<PointI>();


        for(int j=0;j<=boxes;j++)
            for(int i=0;i<boxes;i++)
            {//for H
            	if(etat[2*i+1][2*j]!='@')
            	{
            		boolean ok=true;
            		
        			if(2*j>0)//fermé en haut?
        			{
        				if(2==CompteBordersLibres(new PointI(2*i+1,2*j-1)))ok=false;   
        			}
        			
        			if(2*j<2*boxes)//fermé en bas?
        			{
        				if(2==CompteBordersLibres(new PointI(2*i+1,2*j+1)))ok=false;
        			}	
            		

            		if(ok)lst.add(new PointI(2*i+1,2*j));
            	}
            }

        for(int j=0;j<boxes;j++)
            for(int i=0;i<=boxes;i++)
            {//for V
            	if(etat[2*i][2*j+1]!='@')
        		{
            		boolean ok=true;
        			if(2*i>0)//fermé à gauche?
        			{
        				if(2==CompteBordersLibres(new PointI(2*i-1,2*j+1)))ok=false;
        			}	
        			if(2*i<2*boxes)//fermé à droite?
        			{
        				if(2==CompteBordersLibres
        						(new PointI(2*i+1,2*j+1)))ok=false;
        			}	

            		if(ok)lst.add(new PointI(2*i,2*j+1));
        		}            	
            }

        if(0==lst.size())return null;//ne peut pas fermer

		return lst.get((int)(Math.random()*lst.size()));
	}
	
	private PointI getClickAIClose()
	{
		ArrayList<PointI> lst=new ArrayList<PointI>();

        for(int j=0;j<boxes;j++)
            for(int i=0;i<boxes;i++)
            {
            	int count=0;
            	boolean empty=false;
            	PointI pt=null;
            	
                for(int k=0;k<4;k++)
                {
                	if(etat[2*i+1+voisins[k].x][2*j+1+voisins[k].y]=='@')count++;
                	else 
                    if(etat[2*i+1+voisins[k].x][2*j+1+voisins[k].y]==0)
                    {
                    	empty=true;
                		pt=new PointI(2*i+1+voisins[k].x,2*j+1+voisins[k].y);
                    }
                }
                
                if((count==3)&&empty)//3 traits et un qui reste non pointillé
                	lst.add(pt);
            }


        if(0==lst.size())return null;//ne peut pas fermer

		return lst.get((int)(Math.random()*lst.size()));
	}
	

	private PointI getClickAIRandom()
	{
		ArrayList<PointI> lst=new ArrayList<PointI>();
		
        for(int j=0;j<=boxes;j++)
            for(int i=0;i<boxes;i++)
            {//for H
            	if(etat[2*i+1][2*j]!='@')
            		lst.add(new PointI(2*i+1,2*j));
            }

        for(int j=0;j<boxes;j++)
            for(int i=0;i<=boxes;i++)
            {//for V
            	if(etat[2*i][2*j+1]!='@')
            		lst.add(new PointI(2*i,2*j+1));
            }
        
        

        ///choisis aléatoirement des espaces libres
        return lst.get((int)(Math.random()*lst.size()));
	}
	
	public boolean action(PointI clickpos)
	{
		boolean closed=false;

		if(clickpos.x<0)return TourPremierJoueur;///Mouvement invalide
		if(clickpos.x>=2*boxes+1)return TourPremierJoueur;////Mouvement invalide
		if(clickpos.y<0)return TourPremierJoueur;////Mouvement invalide
		if(clickpos.y>=2*boxes+1)return TourPremierJoueur;////Mouvement invalide
		
		if((clickpos.x%2)==(clickpos.y%2))return TourPremierJoueur;////Mouvement invalide

		if(etat[clickpos.x][clickpos.y]=='@')return TourPremierJoueur;//Il y a déjà une ligne
		
		closed=AjouterLigne(clickpos);
		
		if(!closed)
		TourPremierJoueur=!TourPremierJoueur;
		
		
		if((difficulte>0)&&(!TourPremierJoueur))
		{
		
			while(!fini())
			{
				PointI aiclickpos=getClickAI();
				
				if(!AjouterLigne(aiclickpos))break;///ne peut pas fermer
			}
			

			TourPremierJoueur=!TourPremierJoueur;
		}
		
		
		return TourPremierJoueur;
	}
	
	
	boolean fini()
	{
        for(int j=0;j<boxes;j++)
            for(int i=0;i<boxes;i++)
            {
            	if(etat[2*i+1][2*j+1]==0)return false;
            }
        
        return true;
	}
}

