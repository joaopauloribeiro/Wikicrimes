package org.wikicrimes.util.rotaSegura.testes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.wikicrimes.util.kernelMap.KernelMap;
import org.wikicrimes.util.kernelMap.KernelMapRenderer;
import org.wikicrimes.util.kernelMap.Ponto;
import org.wikicrimes.util.kernelMap.Caminho;
import org.wikicrimes.util.rotaSegura.Grafo;
import org.wikicrimes.util.rotaSegura.LogicaRotaSegura;

/**
 * Representa rotas graficamente, para testes. 
 * 
 * @author victor
 */
public class TesteRotas {

	static final boolean enabled = true;
	static Rectangle limites; //guarda os limites(bounds) da primeira rota, pra aplicar os mesmos limites na segunda
	private final int BORDA = 150;
	private double razao;
	private final int RAIO_VERTICE = 5;
	public static NumberFormat f = NumberFormat.getNumberInstance();
	private PainelTeste painel;
	
	private Color[] cores = {Color.BLUE, Color.RED, Color.GREEN, Color.ORANGE, Color.PINK};
	private Ponto[] pontos;
	private Caminho[] caminhos;
	private Grafo grafo;
	private KernelMap kernel;
	private Map<String,Label> labels = new HashMap<String,Label>();
	
	protected TesteRotas(String titulo, Ponto... pontosProsLimites){
		if(!enabled) return;
		List<Ponto> pontos = Arrays.asList(pontosProsLimites);
		painel = new PainelTeste();
		painel.setPreferredSize(new Dimension(620,620));
		if(limites == null)
			limites = getLimites(pontos);
		
		JFrame frame = new JFrame();
		frame.setTitle(titulo);
		frame.add(painel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public TesteRotas(KernelMap kernel, Grafo grafo){
		this("", grafo.getOrigem(), grafo.getDestino());
//		this( "", new Ponto(kernel.getBounds().x,kernel.getBounds().y), new Ponto(kernel.getBounds().x+kernel.getBounds().width,kernel.getBounds().y+kernel.getBounds().height) );
		grafo = (Grafo)grafo.clone();
		setKernel(kernel);
		setGrafo(grafo);
		painel.repaint();
	}
	
	public void setGrafo(Grafo grafo){
		this.grafo = grafo;
	}
	
	public void setPontos(Ponto... pontos){
		this.pontos = pontos;
	}
	
	public void setRotas(Caminho... rotas) {
		this.caminhos = rotas;
	}
	
	public void setCores(Color... cores){
		this.cores = cores;
	}

	public void setKernel(KernelMap kernel) {
		this.kernel = kernel;
	}
	
	public void setLimites(Ponto... pontos){
		getLimites(Arrays.asList(pontos));
	}
	
	public void addLabel(String key, String texto, int x, int y, Color cor){
		labels.put(key, new Label(texto,x,y,cor));
	}
	
	public Rectangle getLimites(List<Ponto> pontos){
		int menorX = Integer.MAX_VALUE;
		int menorY = Integer.MAX_VALUE;
		int maiorX = Integer.MIN_VALUE;
		int maiorY = Integer.MIN_VALUE;
		for(Ponto p: pontos){
			menorX = Math.min(menorX, (int)p.getX());
			menorY = Math.min(menorY, (int)p.getY());
			maiorX = Math.max(maiorX, (int)p.getX());
			maiorY = Math.max(maiorY, (int)p.getY());
		}
		int amplitudeX = maiorX - menorX;
		int amplitudeY = maiorY - menorY;
		int maiorAmplitude = Math.max(amplitudeX, amplitudeY); 
		int xMedio = (menorX + maiorX)/2;
		int yMedio = (menorY + maiorY)/2;
		Rectangle limites = new Rectangle();
		limites.width = limites.height = maiorAmplitude; //width e height iguais, pra ficar quadrado
		limites.x = menorX - maiorAmplitude/2 + (xMedio-menorX); //x medio fica centralizado
		limites.y = menorY - maiorAmplitude/2 + (yMedio-menorY); //y medio fica centralizado
		return limites;
	}
	
	public void refresh(){
		painel.repaint();
	}
	
	public static void limpar(){
		limites = null;
	}

	class PainelTeste extends JPanel{
	
		public PainelTeste() {
			super();
			MouseAdapter mouseListener = new TesteMouseListener();
			addMouseListener(mouseListener);
			addMouseMotionListener(mouseListener);
		}
		
		@Override
		public void paint(Graphics g) {
	//		pegaLimites(rota1.getPontos());
			setRazao();
	
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			
			pintaKernel(g, kernel);
			pintaGrafo(g, grafo);
			pintaCaminhos(g, caminhos);
			pintaPontos(g, pontos);
			pintaLabels(g, labels);
		}
		
		protected void pintaCaminhos(Graphics g, Caminho... rotas){
			if(rotas!= null){
				int i = 0;
				for(Caminho r : rotas){
					g.setColor(cores[i]);
//					pintaCaminho(g, r, true);
					pintaCaminho(g, r);
					i++;
				}
			}
		}
		
		protected void pintaCaminho(Graphics g, Caminho rota, boolean numerado){
			if(rota != null){
				List<Ponto> pontos = rota.getPontos();
				for(int i=0; i<pontos.size(); i++){
					
					//desenha um ponto
					Ponto p = pontos.get(i);
					int x = xGMtoTeste(p.x);
					int y = yGMtoTeste(p.y);
					g.fillOval(x-2, y-2, 5, 5);
					if(numerado)
						g.drawString(String.valueOf(i), x, y+15);
					
					//desenha uma reta entre dois pontos
					if(i != pontos.size()-1){
						Ponto p2 = pontos.get(i+1);
						int x2 = xGMtoTeste(p2.x);
						int y2 = yGMtoTeste(p2.y);
						g.drawLine(x, y, x2, y2);
					}
				}
			}
		}
		
		protected void pintaCaminho(Graphics g, Caminho rota){
			if(rota!= null){
				Color cor = g.getColor();
				List<Ponto> pontos = rota.getPontos();
				for(int i=0; i<pontos.size(); i++){
					g.setColor(cor);
					//desenha um ponto
					Ponto p = pontos.get(i);
					int x = xGMtoTeste(p.x);
					int y = yGMtoTeste(p.y);
					g.fillOval(x-2, y-2, 5, 5);
					
					//desenha uma reta entre dois pontos
					if(i != pontos.size()-1){
						Ponto p2 = pontos.get(i+1);
						int x2 = xGMtoTeste(p2.x);
						int y2 = yGMtoTeste(p2.y);
						g.drawLine(x, y, x2, y2);
					}
				}
				//bota o perigo no meio da rota
	//			g.setColor(Color.GREEN);
	//			Ponto meio = Ponto.medio(rota.getInicio(), rota.getFim());
	//			int xMeio = xGMtoTeste(meio.x);
	//			int yMeio = yGMtoTeste(meio.y);
	//			g.drawString(f.format(rota.getPerigo()),xMeio, yMeio);
	//			g.setColor(Color.BLUE);
			}
		}
		
		protected void pintaGrafo(Graphics g, Grafo grafo){
			if(grafo != null){
				
				//pinta vertices
				Map<Integer,Ponto> pontos = grafo.getPontos();
				for(int i : pontos.keySet()){
					g.setColor(Color.BLACK);
					Ponto p = pontos.get(i);
					pintaPonto(g, p, RAIO_VERTICE); 
					pintaString(g, String.valueOf(i), p, 5, 1);
				}
				
				//pinta rotas
				List<Caminho> rotas = grafo.getCaminhos();
				g.setColor(Color.BLACK);
				for(Caminho rota : rotas){
					if(kernel != null)
						g.setColor(new LogicaRotaSegura(kernel).isToleravel(rota)? Color.BLACK : Color.RED);
					pintaCaminho(g, rota);
				}
			}
		}
		
		protected void pintaPontos(Graphics g, Ponto... pontos){
			if(pontos!= null){
				int i = 0;
				for(Ponto p : pontos){
					g.setColor(cores[i]);
					pintaPonto(g, p, RAIO_VERTICE);
					i++;
				}
			}
		}
		
		protected void pintaPonto(Graphics g, Ponto p, int raio){
			int x = xGMtoTeste((int)p.getX());
			int y = yGMtoTeste((int)p.getY());
			g.fillOval(x-raio, y-raio, raio*2, raio*2);
		}
		
		protected void pintaString(Graphics g, String s, Ponto p, int raio,int linha){
			int x = xGMtoTeste((int)p.getX());
			int y = yGMtoTeste((int)p.getY());
			g.drawString(s, x+raio, y+raio+10*linha);
		}
		
		protected void pintaString(Graphics g, String s, int posX, int posY, Color cor){
			g.setColor(cor);
			int x = xGMtoTeste(posX);
			int y = yGMtoTeste(posY);
			g.drawString(s, x, y);
		}
		
		protected void pintaLabels(Graphics g, Map<String,Label> labels){
			for(Label label : labels.values())
				pintaString(g, label.texto, label.posicao.x, label.posicao.y, label.cor);
		}
		
		protected void pintaKernel(Graphics g, KernelMap kernel){
			if(kernel != null){
				KernelMapRenderer r = new KernelMapRenderer(kernel);
				Image img = r.pintaKernel();
				Rectangle kBounds = kernel.getBounds();
				int dx1 = xGMtoTeste(limites.x) - BORDA;
				int dy1 = yGMtoTeste(limites.y) - BORDA;
				int dx2 = xGMtoTeste(limites.x + limites.width) + BORDA;
				int dy2 = yGMtoTeste(limites.y + limites.height) + BORDA;
				int sx1 = xTesteToGM(xGMtoTeste(limites.x) - BORDA) - kBounds.x;
				int sy1 = yTesteToGM(yGMtoTeste(limites.y) - BORDA) - kBounds.y;
				int sx2 = xTesteToGM(xGMtoTeste(limites.x + limites.width) + BORDA) - kBounds.x;
				int sy2 = yTesteToGM(yGMtoTeste(limites.y + limites.height) + BORDA) - kBounds.y;
				g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
			}
		}
		
		protected void setRazao(){
			if(limites.width>limites.height)
				razao = (this.getWidth() - BORDA*2.0)/limites.width;
			else
				razao = (this.getHeight() - BORDA*2.0)/limites.height;
		}
		
		protected int xGMtoTeste(int x){
			return (int)((x - limites.x) * razao) + BORDA;
		}
		
		protected int yGMtoTeste(int y){
			return (int)((y - limites.y) * razao) + BORDA;
		}
		
		protected int xTesteToGM(int x){
			return (int)Math.round((x - BORDA)/razao + limites.x); 
		}
		
		protected int yTesteToGM(int y){
			return (int)Math.round((y - BORDA)/razao + limites.y);
		}
		
		class TesteMouseListener extends MouseAdapter{
			@Override
			public void mouseClicked(MouseEvent e) {
				Point mouse = e.getPoint();
				int idVertice = 0;
				Map<Integer, Ponto> m = grafo.getPontos();
				for(int i : grafo.getPontos().keySet()){
					Ponto p = m.get(i);
					Rectangle r = new Rectangle(xGMtoTeste(p.x)-RAIO_VERTICE, yGMtoTeste(p.y)-RAIO_VERTICE, RAIO_VERTICE*2, RAIO_VERTICE*2);
					if(r.contains(mouse)){
						idVertice = i;
						break;
					}
				}
				
				if(idVertice != 0){
					System.out.println( grafo.getDetalhes(m.get(idVertice)) );
				}
			}
			
			@Override
			public void mouseMoved(MouseEvent e) {
				Point mouse = e.getPoint();
				addLabel(POS_MOUSE, "x:" + xTesteToGM(mouse.x) + ", y:" + yTesteToGM(mouse.y), xTesteToGM(0), yTesteToGM(10), Color.BLACK);
				refresh();
			}
			
		}
	}

	static final String POS_MOUSE = "POS_MOUSE";
	
	class Label{
		String texto;
		Ponto posicao;
		Color cor;
		Label(String texto, int x, int y, Color cor){
			this.posicao = new Ponto(x,y);
			this.texto = texto;
			this.cor = cor;
		}
		@Override
		public String toString() {
			return texto;
		}
	}
}