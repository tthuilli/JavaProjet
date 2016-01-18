package DotsAndBoxes;

public 
class FenetreJeu  extends javax.swing.JFrame {

	   private FenetreOption parent;
	   private javax.swing.JLabel TitreScore;
	   private javax.swing.JLabel ValeurScore;
	   private javax.swing.JButton BoutonRejouer;
	   private MyPanel myPanel;

	   
	   
	   public FenetreJeu(EtatJeu game,FenetreOption parent) {
		   this.parent=parent;
	       initComponents(game);
	   }
	   
	   private void initComponents(final EtatJeu game) {
		   TitreScore = new javax.swing.JLabel();
	       ValeurScore = new javax.swing.JLabel();
	       BoutonRejouer = new javax.swing.JButton();
	       myPanel=new MyPanel(game,ValeurScore,TitreScore);
	       
	       BoutonRejouer.setText("Rejouer");
	       

		   this.addWindowListener(new java.awt.event.WindowAdapter() {
				@Override
			    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
			    	parent.setVisible(true);
			        };
			    });

		   
		   
	       
	       BoutonRejouer.addActionListener(new java.awt.event.ActionListener() {
	           public void actionPerformed(java.awt.event.ActionEvent evt) {
	        	   game.reset();
	        	   myPanel.newGame(game);
	           }
	       });
	       
	       
	       setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	       setTitle("Jeu");

	       javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	       getContentPane().setLayout(layout);
	       layout.setHorizontalGroup(
	           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
	           .addGroup(layout.createSequentialGroup()
	               .addContainerGap()
	               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
	        		   .addGroup(layout.createSequentialGroup()
		                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                       .addComponent(BoutonRejouer)
		                       )
                    .addGroup(layout.createSequentialGroup()
 	                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
 	                       .addComponent(TitreScore)
 	                       )
	                   .addGroup(layout.createSequentialGroup()
	                           .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                           .addComponent(ValeurScore)))
	               .addContainerGap(27, Short.MAX_VALUE))

		           .addGroup(layout.createSequentialGroup()
		                   .addComponent(myPanel))
	       );

	       layout.setVerticalGroup(
	           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	           .addGroup(layout.createSequentialGroup()
	               .addContainerGap()
	               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                   .addComponent(BoutonRejouer))
		               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                   .addComponent(TitreScore))
		               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                  .addComponent(ValeurScore)) 
	                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)            
	               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	               .addComponent(myPanel))
	               .addContainerGap(21, Short.MAX_VALUE)   
	               )
	           .addComponent(myPanel)
	       );
	       

	       
	       pack();

	   }
	    
}
