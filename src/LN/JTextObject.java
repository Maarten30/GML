package Usuario;

public class JTextObject extends javax.swing.JTextField {

	private static final long serialVersionUID = 1L;
	
	private boolean esSombra = true;
	private String sombra;


	 public JTextObject(String texto, int columnas) {
	  sombra = texto;  this.setText(texto); this.setColumns(columnas);
	  this.setForeground(java.awt.Color.LIGHT_GRAY);
	  
	  this.addFocusListener(new java.awt.event.FocusListener() {
	   @Override
	   public void focusGained(java.awt.event.FocusEvent e) {
	    if (JTextObject.this.getText().equalsIgnoreCase(sombra)) {
	     JTextObject.this.setCaretPosition(0);
	    }
	   }


	   @Override
	   public void focusLost(java.awt.event.FocusEvent e) {
	    if (JTextObject.this.getText().equalsIgnoreCase("")) {
	     esSombra = true; JTextObject.this.setText(sombra);
	     JTextObject.this.setForeground(java.awt.Color.LIGHT_GRAY);
	    }
	   }   
	  });
	  
	  this.addKeyListener(new java.awt.event.KeyAdapter(){
	   @Override
	   public void keyPressed(java.awt.event.KeyEvent e) {
	    if (esSombra) {
	     esSombra = false; JTextObject.this.setText("");
	     JTextObject.this.setForeground(java.awt.Color.BLACK);
	    }
	   }   
	  });


	  this.addMouseListener(new java.awt.event.MouseAdapter() {
	   @Override
	   public void mouseClicked(java.awt.event.MouseEvent arg0) {
	    if (esSombra) {
	     JTextObject.this.setCaretPosition(0);
	    }
	   }
	  });
	 }
	 
	 public void JTextObject1(String texto, int columnas) {
		// TODO Auto-generated constructor stub
	}

	@Override
	 public void setText(String arg0) {
	  if(esSombra && !arg0.equalsIgnoreCase(sombra)) { 
	   esSombra = false; 
	   JTextObject.this.setForeground(java.awt.Color.BLACK);
	  } super.setText(arg0);
	  
	 }


	 @Override
	 public String getText() {
	  if(esSombra) {
	   return (super.getText().replace(sombra, ""));
	  } return super.getText();
	 }
	 
	 public boolean vacio() {
	  return esSombra;
	 }
	 
	 public void setSombra(String neoSombra) {
	  sombra = neoSombra;
	 }
	}