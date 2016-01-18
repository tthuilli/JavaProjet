package DotsAndBoxes;

import java.awt.Dimension;

public class FenetreOption  extends javax.swing.JFrame {

	   private javax.swing.JButton Jouer;
	   private javax.swing.JButton Console;
	   private FenetreOption fenetreoptions;



	   private javax.swing.JTextField p1nomEdit;
	   private javax.swing.JTextField p2nomEdit;
	   	  
	   private javax.swing.JLabel boxesLabel;	   
	   private javax.swing.JTextField boxesEdit;
	   
	   private javax.swing.JLabel difficulteLabel;	   
	   private javax.swing.JTextField difficulteEdit;

	   
	   private javax.swing.JLabel dotLabel;	   
	   private javax.swing.JTextField dotEdit;

	       
	   public FenetreOption()
	   {
		   fenetreoptions=this;
	       initComponents();
	   }

	   private void initComponents() {
		   Jouer = new javax.swing.JButton();
		   Console = new javax.swing.JButton();
		   
		   

	       p1nomEdit = new javax.swing.JTextField();
	       p1nomEdit.setText("Thibault");
	       
	       p2nomEdit = new javax.swing.JTextField();
	       p2nomEdit.setText("M. Sellami");
	       
		   
	       difficulteLabel = new javax.swing.JLabel();
	       difficulteEdit = new javax.swing.JTextField();

	       boxesLabel = new javax.swing.JLabel();
	       boxesEdit = new javax.swing.JTextField();

	       dotLabel = new javax.swing.JLabel();
	       dotEdit = new javax.swing.JTextField();
	       
	       
		   
	       boxesLabel.setText("Taille du jeu");
	       boxesEdit.setText("3");

	       difficulteLabel.setText("Difficulté (1, 2 ou 3); "
	       		+  " Pour jouer contre un 2ème joueur, entrez la valeur 0");
	       difficulteEdit.setText("3");

	       dotLabel.setText("Pointillés");
	       dotEdit.setText("0");

	       Jouer.setText("Graphique");
	       Console.setText("Console");

	       Jouer.addActionListener(new java.awt.event.ActionListener() {
	           public void actionPerformed(java.awt.event.ActionEvent evt) {
	        	   
	        	   try
	        	   {
		        	   int size= Integer.parseInt(boxesEdit.getText());
		        	   int dots= Integer.parseInt(dotEdit.getText());
		        	   int difficulte= Integer.parseInt(difficulteEdit.getText());
		        	   
		        	   if(((size>0)&&(size<100))&&((difficulte>=0)&&(difficulte<=3)))
		        	   {
			        	   fenetreoptions.setVisible(false);
			               new FenetreJeu(new EtatJeu(size,dots,p1nomEdit.getText(),p2nomEdit.getText(),difficulte),fenetreoptions).setVisible(true);
		        	   }
	        	   }
	        	   catch(Exception e)
	        	   {
	        		   
	        	   }
	        	   
	           }
	       });
	       
	       

	       Console.addActionListener(new java.awt.event.ActionListener() {
	           public void actionPerformed(java.awt.event.ActionEvent evt) {
	        	   
	        	   try
	        	   {
		        	   int size= Integer.parseInt(boxesEdit.getText());
		        	   int dots= Integer.parseInt(dotEdit.getText());
		        	   int difficulte= Integer.parseInt(difficulteEdit.getText());
		        	   
		        	   if(((size>0)&&(size<100))&&((difficulte>=0)&&(difficulte<=3)))
		        	   {
			        	   fenetreoptions.setVisible(false);
			        	   
			        	   new Console(new EtatJeu(size,dots,p1nomEdit.getText(),p2nomEdit.getText(),difficulte));
			        	   
			        	   fenetreoptions.setVisible(true);
		        	   }
	        	   }
	        	   catch(Exception e)
	        	   {
	        		   
	        	   }
	        	   
	           }
	       });
	       
	       
	       setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	       setTitle("Options");

	       

	       javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	       getContentPane().setLayout(layout);
	       layout.setHorizontalGroup(
	           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
	           .addGroup(layout.createSequentialGroup()
	               .addContainerGap()
	               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

	                   .addGroup(layout.createSequentialGroup()
                        .addComponent(boxesEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boxesLabel))
	                           
	                   .addGroup(layout.createSequentialGroup()
                        .addComponent(dotEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dotLabel))
                        
                    .addGroup(layout.createSequentialGroup()
	                       .addComponent(difficulteEdit)
	                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                       .addComponent(difficulteLabel))
	                       
	                   .addGroup(layout.createSequentialGroup()
                        .addComponent(p1nomEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(p2nomEdit))
 	                           
	                   .addGroup(layout.createSequentialGroup()
                        .addComponent(Jouer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Console))
		           .addGroup(layout.createSequentialGroup())
	                           )
	               .addContainerGap())

	       );

	       layout.setVerticalGroup(
	           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	           .addGroup(layout.createSequentialGroup()
	               .addContainerGap()
	               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                   .addComponent(boxesEdit)
	                   .addComponent(boxesLabel)) 
		           .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                   .addComponent(dotEdit)
	                   .addComponent(dotLabel)) 
		           .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                   .addComponent(difficulteEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                   .addComponent(difficulteLabel))
	               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                   .addComponent(p1nomEdit)
	                   .addComponent(p2nomEdit)) 
	               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)           
	               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	            		   .addComponent(Jouer).addComponent(Console))
	               .addContainerGap(21, Short.MAX_VALUE)   
	               )
	       );
	       

	       
	      pack();
	       


	   }
	   
	   
}
