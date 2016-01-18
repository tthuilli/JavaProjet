package DotsAndBoxes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class MyPanel extends JPanel  implements MouseListener,MouseMotionListener {

	PointI mousepos;
	boolean click;
	
	Font font;
	EtatJeu EtatJeu;
	
	int padpix=50;
	int fontsz;

	private javax.swing.JLabel ValeurScore;
	private javax.swing.JLabel TitreScore;
	

	
	public void  newGame(EtatJeu EtatJeu)
	{
		this.EtatJeu=EtatJeu;
		MaJScore(); 
		repaint(); // méthode swing
	}
	
	
    public MyPanel(EtatJeu EtatJeux, javax.swing.JLabel ValeurScore, javax.swing.JLabel TitreScore) {
    	
    	this.ValeurScore=ValeurScore;    	
    	this.TitreScore=TitreScore;
    	
    	this.EtatJeu=EtatJeux;
    	fontsz=12;
    	font=new Font( "SansSerif", Font.BOLD, fontsz);
    	
    	mousepos=new PointI(0,0);

        setBorder(BorderFactory.createLineBorder(Color.black));
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        setDoubleBuffered(true);    
        
        MaJScore();
    }
    
    PointI LineLaPlusProche(PointD f)
    {
        int boxes=EtatJeu.getBoxes();
    	PointD pos=new PointD(f.x,f.y);

		int xi,yi;
		

        if(pos.x<0)pos.x=0;
        if(pos.x>0.999)pos.x=0.999;

        if(pos.y<0)pos.y=0;
        if(pos.y>0.999)pos.y=0.999;

    	double dx=pos.x*(boxes)-Math.round(pos.x*(boxes));
    	double dy=pos.y*(boxes)-Math.round(pos.y*(boxes));

        //System.out.println(pos.x+" "+pos.y+" : "+dx+" " +dy+" : "+pos.x*(1+boxes)+" "+pos.y*(1+boxes));
        
    	if(Math.abs(dx)<Math.abs(dy))
    	{///plus près de y
    		
    		 xi=(int)Math.round(pos.x*(boxes));
    		 xi=xi*2;

    		 yi=(int)(pos.y*(boxes));
    		 yi=2*yi+1;

    	}
    	else
    	{/// le plus près de x

	   		 xi=(int)(pos.x*(boxes));
	   		 xi=xi*2+1;
	
	   		 yi=(int)Math.round(pos.y*(boxes));
	   		 yi=2*yi;
    	}
    	

     	return new PointI(xi,yi);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(500,500);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);       

        char [][] state=EtatJeu.getEtat();
        int boxes=EtatJeu.getBoxes();
                
        int w=getSize().width;
        int h=getSize().height;
        int cond=((h<w)?h:w)-10; //condition
        int s=((h<w)?h:w)-padpix;

        int newfontsz=(s)/(1+boxes);
        if(fontsz!=newfontsz)
        {
        	fontsz=newfontsz;
        	font=new Font( "SansSerif", Font.BOLD,fontsz );
        }

        PointI d=new PointI ((w-s)/2,(h-s)/2);

        g.setColor(Color.GRAY);
        g.fillRect(0,0,w,h);

        g.setColor(Color.white);
        g.fillRect((w-cond)/2, (h-cond)/2, cond, cond);         ///********

        int dotsz=(int)(s*0.01);

        PointD f=new PointD( (mousepos.x-d.x)*1.0/s, (mousepos.y-d.y)*1.0/s);


        g.setColor(Color.black);
        
        
        for(int j=0;j<=boxes;j++)
            for(int i=0;i<boxes;i++)
            {
                int pxs= d.x+(i*s)/boxes;
                int pxe= d.x+((i+1)*s)/boxes;
                int py= d.y+(j*s)/boxes;

                if(state[2*i+1][2*j]=='@')
                {
	                g.drawLine(pxs, py, pxe, py);
                }
                else
                if(state[2*i+1][2*j]=='.')
                {
	                g.drawLine((5*pxs+pxe)/6, py, (2*pxs+pxe)/3, py);

	                g.drawLine((pxs+2*pxe)/3, py, (pxs+5*pxe)/6, py);
                }
            }
        

        for(int j=0;j<boxes;j++)
            for(int i=0;i<=boxes;i++)
            {
                int px= d.x+(i*s)/boxes;
                int pys= d.y+(j*s)/boxes;
                int pye= d.y+((j+1)*s)/boxes;

                if(state[2*i][2*j+1]=='@')
                {
	                g.drawLine(px, pys, px, pye);
                }
                else
                if(state[2*i][2*j+1]=='.')
                {
	                g.drawLine(px, (5*pys+pye)/6, px, (2*pys+pye)/3);
	                g.drawLine(px, (pys+2*pye)/3, px, (pys+5*pye)/6);
                }
            }

    	
        {///dessin ligne entre les points
        	
	    	if(EtatJeu.TourPremierJoueur())
	            g.setColor(Color.red);
	    	else
	            g.setColor(Color.blue);
        	PointI line=LineLaPlusProche(f);

            int xi=line.x;
            int yi=line.y;
            
			if(state[xi][yi]!='@')
			{
			    if(((xi%2)==1)&&((yi%2)==0))
			    {
			        int pxs= d.x+(xi/2*s)/boxes;
			        int pxe= d.x+((xi/2+1)*s)/boxes;
			        int py= d.y+(yi/2*s)/boxes;			
			        
			         g.fillRect(pxs, py-dotsz, pxe-pxs, 2*+dotsz);
			    }
			
			    if(((yi%2)==1)&&((xi%2)==0))
			    {
			        int px= d.x+(xi/2*s)/boxes;
			        int pys= d.y+(yi/2*s)/boxes;
			        int pye= d.y+((yi/2+1)*s)/boxes;
			
			        g.fillRect(px-dotsz, pys, 2*dotsz, pye-pys);
			    }
			}

            
        }

        g.setColor(Color.black);

        {
        	Graphics2D gO = (Graphics2D)g;
            gO.setFont(font);
			FontMetrics metrics = gO.getFontMetrics(font);
            
	        for(int j=0;j<boxes;j++)
	            for(int i=0;i<boxes;i++)
	            if(state[2*i+1][2*j+1]!=0)
	            {
	            	String txt=String.valueOf(state[2*i+1][2*j+1]);
	            	
	            	if(state[2*i+1][2*j+1]==EtatJeu.getP1())
	                    gO.setColor(Color.red);
	            	else
	            		gO.setColor(Color.blue);

					int hgt = metrics.getHeight();///centrer le texte
					int adv = metrics.stringWidth(txt);

	                int px= d.x+((2*i+1)*s)/(2*boxes)-adv/2;
	                int py= d.y+((2*j+1)*s)/(2*boxes)+hgt/4;

	                gO.drawString(txt, px, py);
	            }
        }
        

        g.setColor(Color.black);
        for(int j=0;j<=boxes;j++)
            for(int i=0;i<=boxes;i++)
            {

                int px= d.x+(i*s)/boxes;
                int py= d.y+(j*s)/boxes;

                g.fillOval(px-dotsz, py-dotsz, 2*dotsz, 2*dotsz);                    
            }
    }


public void mousePressed(MouseEvent e) 
{
    if(e.getButton() == MouseEvent.BUTTON1)
    {
    	mousepos=new PointI(e.getX(),e.getY());
    	click=true;


    	
        int w=getSize().width;
        int h=getSize().height;
        int s=((h<w)?h:w)-padpix;

        PointD d=new PointD ((w-s)/2,(h-s)/2);
        		
        
        PointD f=new PointD( (mousepos.x-d.x)*1.0/s, (mousepos.y-d.y)*1.0/s);

        
    	PointI line=LineLaPlusProche(f);

        EtatJeu.action(line);
        
        MaJScore();
    }


}


private void MaJScore()
{
	int p1score=EtatJeu.scoreP(EtatJeu.getP1());
	int p2score=EtatJeu.scoreP(EtatJeu.getP2());


    if(EtatJeu.fini())
    {
    	if(p1score>p2score)
	    	 TitreScore.setText(EtatJeu.getP1Nom()+ " à gagné");
    	else
    	if(p1score<p2score)
    		TitreScore.setText(EtatJeu.getP2Nom()+ " à gagné");
    	else
    		TitreScore.setText("Score final : égalité !");
    }
    else
    TitreScore.setText("Score");
    
	ValeurScore.setText(EtatJeu.getP1Nom()+" "+p1score+" : "+p2score+ " "+EtatJeu.getP2Nom());
	repaint();
}

public void mouseReleased(MouseEvent e)
{
    if(e.getButton() == MouseEvent.BUTTON1)
    {
    	click=false;
    	repaint(); // méthode swing
    }	 
}

public void mouseEntered(MouseEvent e) 
{
	
}

public void mouseExited(MouseEvent e) 
{
	
}

public void mouseClicked(MouseEvent e) 
{
	
}



@Override
public void mouseDragged(MouseEvent e) {
	
}



@Override
public void mouseMoved(MouseEvent e) {
	mousepos=new PointI(e.getX(),e.getY());
	repaint();
	
}
}