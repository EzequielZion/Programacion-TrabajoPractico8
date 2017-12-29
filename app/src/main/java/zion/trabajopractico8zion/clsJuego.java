package zion.trabajopractico8zion;


import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.MotionEvent;

import org.cocos2d.actions.interval.Animate;
import org.cocos2d.actions.interval.MoveTo;
import org.cocos2d.actions.interval.ScaleBy;
import org.cocos2d.layers.Layer;
import org.cocos2d.nodes.Animation;
import org.cocos2d.nodes.Director;
import org.cocos2d.nodes.Label;
import org.cocos2d.nodes.Scene;
import org.cocos2d.nodes.Sprite;
import org.cocos2d.opengl.CCGLSurfaceView;
import org.cocos2d.types.CCColor3B;
import org.cocos2d.types.CCPoint;
import org.cocos2d.types.CCSize;

import java.util.ArrayList;
import java.util.Random;

public class clsJuego
{
   CCGLSurfaceView _VistaDelJuego;
   CCSize PantallaDelDevice;
   Sprite Cocinero;
   Sprite AuxPancitoAbajo;
   Sprite PancitoAbajo;
   Sprite Comida;
   Sprite Fondo;
   Sprite CocineroIntro;
   Sprite Dialogo;
   Sprite BotonIntro;
   Sprite BotonReiniciar;
   Sprite AuxPancitoArriba;
   Sprite PancitoArriba;
   
   int CantIngredientesHamburguesa;
   int ComprobadorIngredientes;
   int CantIntro;
   int CantIngredientesDesperdiciados;
   
   int IngredienteAEvitar1 = 3;
   int IngredienteAEvitar2 = 3;
   int IngredienteAEvitar3 = 3;
   
   Label LabelCantIngredientesUsados;
   Label LabelCantIngredientesDesperdiciados;
   
   float AltoSprite;
   float AlturaCocinero;
   float AnchoCocinero;
   float PosicionInicialX, PosicionInicialY, PosicionFinalY;
   Random GeneradorDeAzar = new Random();
   boolean CocineroToqueteado;
   boolean SePusoElPanDeAbajo = false;
   boolean SeLargoElPanDeArriba = false;
   boolean SePusoElPanDeArriba = false;
   boolean BotonToqueteado = false;
   boolean BotonReinicioToqueteado = false;
   Context _ContextoDelJuego;
   Animation SeQuemo = new Animation("SeQuemoBro", 0.2f, "1.png", "2.png", "3.png", "4.png", "5.png", "6.png", "7.png", "8.png");
   
   
   public clsJuego(CCGLSurfaceView VistaDelJuego, Context ContextoDelJuego)
   {
	  _VistaDelJuego = VistaDelJuego;
	  _ContextoDelJuego = ContextoDelJuego;
   }
   
   public void ComenzarJuego()
   {
	  Log.d("Comienzaeljuego", "daleeee");
	  
	  Director.sharedDirector().attachInView(_VistaDelJuego);
	  PantallaDelDevice = Director.sharedDirector().displaySize();
	  Director.sharedDirector().runWithScene(EscenaInicio());
	  CocineroToqueteado = false;
   }
   
   private Scene EscenaDelJuego()
   {
	  Scene EscenaADevolver = Scene.node();
	  
	  CapaDelFondo MiCapaDelFondo = new CapaDelFondo();
	  
	  CapaDelFrente MiCapaDelFrente = new CapaDelFrente();
	  
	  EscenaADevolver.addChild(MiCapaDelFondo, -10);
	  EscenaADevolver.addChild(MiCapaDelFrente, 10);
	  
	  return EscenaADevolver;
   }
   
   private Scene EscenaInicio()
   {
	  Scene EscenaADevolver = Scene.node();
	  CapaInicio MiCapaInicio = new CapaInicio();
	  EscenaADevolver.addChild(MiCapaInicio);
	  return EscenaADevolver;
   }
   
   private Scene EscenaPerdiste()
   {
	  Scene EscenaADevolver = Scene.node();
	  CapaPerdiste MiCapaPerdiste = new CapaPerdiste();
	  EscenaADevolver.addChild(MiCapaPerdiste);
	  return EscenaADevolver;
   }
   
   private Scene EscenaGanaste()
   {
	  Scene EscenaADevolver = Scene.node();
	  CapaGanaste MiCapaGanaste = new CapaGanaste();
	  EscenaADevolver.addChild(MiCapaGanaste);
	  return EscenaADevolver;
   }
   
   class CapaInicio extends Layer
   {
	  public CapaInicio()
	  {
		 CantIntro = -1;
		 PonerFondoIntro();
		 PonerDialogoDeTexto();
		 CambiarBoton("botonrespuesta1.png");
		 MensajeBienvenida();
		 MediaPlayer ItsAMe = MediaPlayer.create(_ContextoDelJuego, R.raw.itsame);
		 ItsAMe.start();
		 this.setIsTouchEnabled(true);
	  }
	  
	  private void PonerFondoIntro()
	  {
		 Fondo = Sprite.sprite("cocinaintro.png");
		 Float FactorAncho, FactorAlto;
		 FactorAncho = PantallaDelDevice.width / Fondo.getWidth();
		 FactorAlto = PantallaDelDevice.height / Fondo.getHeight();
		 
		 Fondo.setPosition(PantallaDelDevice.width / 2, PantallaDelDevice.height / 2);
		 Fondo.runAction(ScaleBy.action(0.01f, FactorAncho, FactorAlto));
		 super.addChild(Fondo);
	  }
	  
	  private void PonerDialogoDeTexto()
	  {
		 Dialogo = Sprite.sprite("dialogo.png");
		 Dialogo.setPosition(PantallaDelDevice.width / 2 + 50, PantallaDelDevice.height / 2 - 75);
		 super.addChild(Dialogo);
	  }
	  
	  private void CambiarDialogoDeTexto(String Imagen)
	  {
		 Dialogo = Sprite.sprite(Imagen);
		 Dialogo.setPosition(PantallaDelDevice.width / 2 + 50, PantallaDelDevice.height / 2 - 75);
		 super.addChild(Dialogo);
	  }
	  
	  private void CambiarBoton(String Imagen)
	  {
		 BotonIntro = Sprite.sprite(Imagen);
		 BotonIntro.setPosition(BotonIntro.getWidth() / 2 + 50, BotonIntro.getHeight() / 2 + 50);
		 super.addChild(BotonIntro);
	  }
	  
	  public void MensajeBienvenida()
	  {
		 CocineroIntro = Sprite.sprite("cocinerointro.png");
		 CocineroIntro.setPosition(PantallaDelDevice.width - CocineroIntro.getWidth() / 2, CocineroIntro.getHeight() / 2);
		 super.addChild(CocineroIntro);
	  }
	  
	  @Override
	  public boolean ccTouchesBegan(MotionEvent event)
	  {
		 if (EstaEntre((int) event.getX(),
				  (int) (BotonIntro.getPositionX() - BotonIntro.getWidth() / 2),
				  (int) (BotonIntro.getPositionX() + BotonIntro.getWidth() / 2)))
		 {
			if (EstaEntre((int) (PantallaDelDevice.getHeight() - event.getY() - BotonIntro.getHeight() / 2),
					 (int) (BotonIntro.getPositionY() - BotonIntro.getHeight() / 2),
					 (int) (BotonIntro.getPositionY() + BotonIntro.getHeight() / 2)))
			{
			   BotonToqueteado = true;
			}
		 }
		 if (BotonToqueteado)
		 {
			CantIntro++;
			switch (CantIntro)
			{
			   case 0:
				  CocineroIntro = Sprite.sprite("cocinerointroenojado.png");
				  CocineroIntro.setPosition(PantallaDelDevice.width - CocineroIntro.getWidth() / 2, CocineroIntro.getHeight() / 2);
				  super.addChild(CocineroIntro);
				  CambiarDialogoDeTexto("dialogo1.png");
				  CambiarBoton("botonrespuesta2.png");
				  BotonToqueteado = false;
				  break;
			   case 1:
				  MensajeBienvenida();
				  MediaPlayer No = MediaPlayer.create(_ContextoDelJuego, R.raw.wawa);
				  No.start();
				  CambiarDialogoDeTexto("dialogo2.png");
				  CambiarBoton("botonrespuesta3.png");
				  BotonToqueteado = false;
				  break;
			   case 2:
				  CambiarDialogoDeTexto("dialogo3.png");
				  CambiarBoton("botonrespuesta4.png");
				  BotonToqueteado = false;
				  break;
			   case 3:
				  MediaPlayer Wuju = MediaPlayer.create(_ContextoDelJuego, R.raw.wuju);
				  Wuju.start();
				  BotonToqueteado = false;
				  Director.sharedDirector().replaceScene(EscenaDelJuego());
				  break;
			}
		 }
		 return true;
	  }
   }
   
   class CapaDelFondo extends Layer
   {
	  public CapaDelFondo()
	  {
		 PonerImagenFondo();
	  }
	  
	  private void PonerImagenFondo()
	  {
		 Fondo = Sprite.sprite("cocina.png");
		 Float FactorAncho, FactorAlto;
		 FactorAncho = PantallaDelDevice.width / Fondo.getWidth();
		 FactorAlto = PantallaDelDevice.height / Fondo.getHeight();
		 
		 Fondo.setPosition(PantallaDelDevice.width / 2, PantallaDelDevice.height / 2);
		 Fondo.runAction(ScaleBy.action(0.01f, FactorAncho, FactorAlto));
		 super.addChild(Fondo);
	  }
   }
   
   class CapaDelFrente extends Layer
   {
	  ArrayList<Sprite> ArrayComida;
	  ArrayList<IngredienteAVerificar> ListaChequeoDeValores;
	  
	  Sprite[] ArrayHamburguesaFormada = new Sprite[16];
	  
	  
	  public CapaDelFrente()
	  {
		 PonerCocinero();
		 PonerPanDeAbajo();
		 PonerLabels();
		 ArrayComida = new ArrayList<Sprite>();
		 ListaChequeoDeValores = new ArrayList<IngredienteAVerificar>();
		 CantIngredientesHamburguesa = -1;
		 ComprobadorIngredientes = -1;
		 CantIngredientesDesperdiciados = 0;
		 super.schedule("PonerComida", 1f);
		 super.schedule("DeteccionColisionesPanDeAbajo", 0.25f);
		 super.schedule("DeteccionColisionesPanDeArriba", 0.25f);
		 super.schedule("DeteccionColisiones", 0.25f);
		 super.schedule("PasoDeLargo", 0.25f);
		 super.schedule("ComprobacionIngredientes", 0.25f);
		 
		 MediaPlayer MusicaDeFondo = MediaPlayer.create(_ContextoDelJuego, R.raw.allstar);
		 MusicaDeFondo.start();
		 MusicaDeFondo.setVolume(0.3f, 0.3f);
		 MusicaDeFondo.setLooping(true);
		 
		 this.setIsTouchEnabled(true);
	  }
	  
	  public void PonerCocinero()
	  {
		 Log.d("Cocinero", "Puse el cocinero");
		 Cocinero = Sprite.sprite("cocinero.png");
		 CCPoint PosicionInicial1 = new CCPoint();
		 AlturaCocinero = Cocinero.getHeight();
		 AnchoCocinero = Cocinero.getWidth();
		 
		 PosicionInicial1.x = PantallaDelDevice.width / 2;
		 PosicionInicial1.y = (float) 0;
		 PosicionInicial1.y += AlturaCocinero / 2;
		 
		 Cocinero.setPosition(PosicionInicial1.x, PosicionInicial1.y);
		 super.addChild(Cocinero);
	  }
	  
	  public void PonerPanDeAbajo()
	  {
		 AuxPancitoAbajo = Sprite.sprite("pandeabajo.png");
		 float PosicionInicialX, PosicionInicialY, PosicionFinalY;
		 PosicionInicialX = GeneradorDeAzar.nextInt((int) (PantallaDelDevice.width - AuxPancitoAbajo.getWidth() - Cocinero.getWidth() / 2 - 77));
		 PosicionInicialX += AuxPancitoAbajo.getWidth() / 2;
		 PosicionInicialY = PantallaDelDevice.getHeight() + AuxPancitoAbajo.getHeight();
		 PosicionFinalY = 0f;
		 
		 AuxPancitoAbajo.setPosition(PosicionInicialX, PosicionInicialY);
		 AuxPancitoAbajo.runAction(MoveTo.action(6, PosicionInicialX, PosicionFinalY));
		 super.addChild(AuxPancitoAbajo);
	  }
	  
	  public void PonerPanDeArriba()
	  {
		 AuxPancitoArriba = Sprite.sprite("pandearriba.png");
		 float PosicionInicialX, PosicionInicialY, PosicionFinalY;
		 PosicionInicialX = GeneradorDeAzar.nextInt((int) (PantallaDelDevice.width - AuxPancitoArriba.getWidth() - Cocinero.getWidth() / 2 - 77));
		 PosicionInicialX += AuxPancitoArriba.getWidth() / 2;
		 PosicionInicialY = PantallaDelDevice.getHeight() + AuxPancitoArriba.getHeight();
		 PosicionFinalY = 0f;
		 
		 AuxPancitoArriba.setPosition(PosicionInicialX, PosicionInicialY);
		 AuxPancitoArriba.runAction(MoveTo.action(6, PosicionInicialX, PosicionFinalY));
		 super.addChild(AuxPancitoArriba, 30);
	  }
	  
	  public void PonerComida(float Tiempo)
	  {
		 int Opcion = GeneradorDeAzar.nextInt(4);
		 switch (Opcion)
		 {
			case 0:
			   Comida = Sprite.sprite("hamburguesa.png");
			   PonerPostaComidaBro(Comida, 0);
			   break;
			case 1:
			   Comida = Sprite.sprite("queso.png");
			   PonerPostaComidaBro(Comida, 1);
			   break;
			case 2:
			   Comida = Sprite.sprite("lechuga.png");
			   PonerPostaComidaBro(Comida, 2);
			   break;
			case 3:
			   Comida = Sprite.sprite("tomate.png");
			   PonerPostaComidaBro(Comida, 3);
			   break;
		 }
	  }
	  
	  public void PonerPostaComidaBro(Sprite Food, int Valor)
	  {
		 PosicionInicialX = GeneradorDeAzar.nextInt((int) (PantallaDelDevice.width - Food.getWidth() - Cocinero.getWidth()));
		 PosicionInicialX += Food.getWidth() / 2;
		 PosicionInicialY = PantallaDelDevice.getHeight() + Food.getHeight();
		 PosicionFinalY = 0f;
		 ArrayComida.add(Food);
		 
		 AltoSprite = Food.getHeight();
		 
		 Food.setPosition(PosicionInicialX, PosicionInicialY);
		 
		 Food.runAction(MoveTo.action(6, PosicionInicialX, PosicionFinalY));
		 IngredienteAVerificar Ingredientito = new IngredienteAVerificar(Food, Valor);
		 ListaChequeoDeValores.add(Ingredientito);
		 super.addChild(Food, 29);
		 
	  }
	  
	  public void PonerLabels()
	  {
	     LabelCantIngredientesUsados = Label.label("Ingredientes usados: ", "Verdana", 30);
		 LabelCantIngredientesDesperdiciados = Label.label("Ingredientes desperdiciados: ", "Verdana", 30);
		 
		 float AnchoDelLabelUsados, AnchoDelLabelDesperdiciados, AltoDelLabelUsados, AltoDelLabelDesperdiciados;
		 AnchoDelLabelUsados = LabelCantIngredientesUsados.getWidth();
		 AnchoDelLabelDesperdiciados = LabelCantIngredientesDesperdiciados.getWidth();
		 AltoDelLabelUsados = LabelCantIngredientesUsados.getHeight();
		 AnchoDelLabelDesperdiciados = LabelCantIngredientesDesperdiciados.getHeight();
		 
		 LabelCantIngredientesUsados.setPosition(PantallaDelDevice.width / 2, PantallaDelDevice.height - AltoDelLabelUsados);
		 LabelCantIngredientesDesperdiciados.setPosition(PantallaDelDevice.width / 2, PantallaDelDevice.height - AltoDelLabelUsados - 35);
	  
		 super.addChild(LabelCantIngredientesUsados, 12);
		 super.addChild(LabelCantIngredientesDesperdiciados, 12);
	  
	  }
	  
	  @Override
	  public boolean ccTouchesBegan(MotionEvent event)
	  {
		 Log.d("Toque comienza", "X: " + event.getX() + " - Y: " + event.getY());
		 //Ahora me fijo cuál de los dos sprites toca el usuario
		 if (EstaEntre((int) event.getX(),
				  (int) (Cocinero.getPositionX() - Cocinero.getWidth() / 2),
				  (int) (Cocinero.getPositionX() + Cocinero.getWidth() / 2)))
		 {
			if (EstaEntre((int) (PantallaDelDevice.getHeight() - event.getY() - Cocinero.getHeight() / 2),
					 (int) (Cocinero.getPositionY() - Cocinero.getHeight() / 2),
					 (int) (Cocinero.getPositionY() + Cocinero.getHeight() / 2)))
			{
			   CocineroToqueteado = true;
			}
		 }
		 return true;
	  }
	  
	  @Override
	  public boolean ccTouchesMoved(MotionEvent event)
	  {
		 Log.d("Toque mueve", "X: " + event.getX() + " - Y: " + event.getY());
		 if (CocineroToqueteado)
		 {
			boolean MuevoElCocinero = true;
			MoverSprite(Cocinero, event.getX(), Cocinero.getHeight() / 2, MuevoElCocinero);
			
			MuevoElCocinero = false;
			if (SePusoElPanDeAbajo) //Este if está porque sino estuviese y moviera el cocinero antes de que colisione con el pan de abajo, llamaría a un método mandando un Sprite que en el momento no existe.
			{
			   MoverSprite(PancitoAbajo, event.getX() - 77, PancitoAbajo.getPositionY(), MuevoElCocinero);
			}
			if (CantIngredientesHamburguesa > -1)
			{
			   for (int i = 0; i <= CantIngredientesHamburguesa; i++)
			   {
				  MoverSprite(ArrayHamburguesaFormada[i], event.getX() - 77, ArrayHamburguesaFormada[i].getPositionY(), MuevoElCocinero);
			   }
			}
			if (SePusoElPanDeArriba)
			{
			   MoverSprite(PancitoArriba, event.getX() - 77, PancitoArriba.getPositionY(), MuevoElCocinero);
			   
			}
			
		 }
		 
		 return true;
	  }
	  
	  void MoverSprite(Sprite SpriteAMover, float DestinoX, float DestinoY, boolean _MuevoElCocinero)
	  {
		 SpriteAMover.setPosition(DestinoX, DestinoY);
		 if (_MuevoElCocinero)
		 {
			MantenerDentroDeLaPantalla(SpriteAMover, DestinoX, DestinoY);
		 }
		 else
		 {
			MantenerDentroDeLaPantallaHamburguesa(SpriteAMover, DestinoX, DestinoY);
		 }
	  }
	  
	  void MantenerDentroDeLaPantalla(Sprite SpriteCheto, float PosicionFinalX, float PosicionFinalY)
	  {
		 if (PosicionFinalX < SpriteCheto.getWidth() / 2)
		 {
			//Se fue a la izquierda
			PosicionFinalX = SpriteCheto.getWidth() / 2;
		 }
		 if (PosicionFinalX > PantallaDelDevice.getWidth() - SpriteCheto.getWidth() / 2)
		 {
			//Se fue a la derecha
			PosicionFinalX = PantallaDelDevice.getWidth() - SpriteCheto.getWidth() / 2;
		 }
		 SpriteCheto.setPosition(PosicionFinalX, PosicionFinalY);
	  }
	  
	  void MantenerDentroDeLaPantallaHamburguesa(Sprite SpriteCheto, float PosicionFinalX, float PosicionFinalY)
	  {
		 float LimiteDerecha = (Cocinero.getWidth() / 2) + 77;
		 float LimiteIzquierda = (Cocinero.getWidth() / 2) - 77;
		 
		 if (PosicionFinalX < LimiteIzquierda)
		 {
			//Se fue a la izquierda
			PosicionFinalX = LimiteIzquierda;
		 }
		 if (PosicionFinalX > PantallaDelDevice.getWidth() - LimiteDerecha)
		 {
			//Se fue a la derecha
			PosicionFinalX = PantallaDelDevice.getWidth() - LimiteDerecha;
		 }
		 SpriteCheto.setPosition(PosicionFinalX, PosicionFinalY);
	  }
	  
	  void DeteccionColisiones(float Tiempo)
	  {
		 if (!ArrayComida.isEmpty())
		 {
			for (Sprite AlimentoAVerificar : ArrayComida)
			{
			   if (SePusoElPanDeAbajo)
			   {
				  if (CantIngredientesHamburguesa < 0)
				  {
					 if (InterseccionEntreSprites(PancitoAbajo, AlimentoAVerificar))
					 {
						CantIngredientesHamburguesa++;
						Sprite AuxSprite = AlimentoAVerificar;
						super.removeChild(AlimentoAVerificar, true);
						ArrayComida.remove(AlimentoAVerificar);
						super.addChild(AuxSprite, 20);
						
						float PosicionY = PancitoAbajo.getPositionY() + AltoSprite / 4;
						ArrayHamburguesaFormada[CantIngredientesHamburguesa] = AuxSprite;
						ArrayHamburguesaFormada[CantIngredientesHamburguesa].setPosition(Cocinero.getPositionX() - 77, PosicionY);
						
						LabelCantIngredientesUsados.setString("Ingredientes usados: " + (CantIngredientesHamburguesa + 1));
					 
					 }
				  }
				  else if (CantIngredientesHamburguesa <= 14)
				  {
					 if (InterseccionEntreSprites(AlimentoAVerificar, ArrayHamburguesaFormada[CantIngredientesHamburguesa]))
					 {
						CantIngredientesHamburguesa++;
						
						Sprite AuxSprite = AlimentoAVerificar;
						super.removeChild(AlimentoAVerificar, true);
						super.addChild(AuxSprite, 20);
						float PosicionY = ArrayHamburguesaFormada[0].getPositionY() - AltoSprite / 4;
						ArrayComida.remove(AlimentoAVerificar);
						ArrayHamburguesaFormada[CantIngredientesHamburguesa] = AuxSprite;
						for (int i = 0; i <= CantIngredientesHamburguesa; i++)
						{
						   PosicionY += AltoSprite / 4;
						   ArrayHamburguesaFormada[i].setPosition(Cocinero.getPositionX() - 77, PosicionY);
						}
						LabelCantIngredientesUsados.setString("Ingredientes usados: " + (CantIngredientesHamburguesa + 1));
					 }
				  }
				  else
				  {
					 super.unschedule("PonerComida");
					 super.unschedule("DeteccionColisiones");
					 if (SeLargoElPanDeArriba == false)
					 {
						PonerPanDeArriba();
						SeLargoElPanDeArriba = true;
					 }
					 super.removeChild(AlimentoAVerificar, true);
				  }
			   }
			}
		 }
	  }
	  
	  void ComprobacionIngredientes(float Tiempo)
	  {
		 for (IngredienteAVerificar Ingredientito : ListaChequeoDeValores)
		 {
			for (int i = 0; i <= CantIngredientesHamburguesa; i++)
			{
			   if (Ingredientito._SpriteQueSera == ArrayHamburguesaFormada[i])
			   {
				  if (Ingredientito._Valor == IngredienteAEvitar1 || Ingredientito._Valor == IngredienteAEvitar2 || Ingredientito._Valor == IngredienteAEvitar3)
				  {
					 Director.sharedDirector().replaceScene(EscenaPerdiste());
				  }
			   }
			}
		 }
	  }
	  
	  void DeteccionColisionesPanDeAbajo(float Tiempo)
	  {
		 if (InterseccionEntreSprites(Cocinero, AuxPancitoAbajo))
		 {
			PancitoAbajo = AuxPancitoAbajo;
			super.removeChild(AuxPancitoAbajo, true);
			super.addChild(PancitoAbajo);
			PancitoAbajo.setPosition(Cocinero.getPositionX() - 77, AlturaCocinero - 25);
			SePusoElPanDeAbajo = true;
			super.unschedule("DeteccionColisionesPanDeAbajo");
		 }
	  }
	  
	  void DeteccionColisionesPanDeArriba(float Tiempo)
	  {
		 if (InterseccionEntreSprites(ArrayHamburguesaFormada[CantIngredientesHamburguesa], AuxPancitoArriba))
		 {
			PancitoArriba = AuxPancitoArriba;
			super.removeChild(AuxPancitoArriba, true);
			super.addChild(PancitoArriba);
			PancitoArriba.setPosition(Cocinero.getPositionX() - 77, ArrayHamburguesaFormada[CantIngredientesHamburguesa].getPositionY() + PancitoArriba.getHeight() / 4);
			SePusoElPanDeArriba = true;
			super.unschedule("DeteccionColisionesPanDeAriba");
			Director.sharedDirector().replaceScene(EscenaGanaste());
		 }
	  }
	  
	  void PasoDeLargo(float Tiempo)
	  {
		 for (Sprite AlimentoAVerificar : ArrayComida)
		 {
			float Limite = Cocinero.getHeight() + CantIngredientesHamburguesa * AltoSprite / 5;
			float Limite2 = Cocinero.getHeight() / 2;
			if (AlimentoAVerificar.getPositionY() < Limite)
			{
			   AlimentoAVerificar.runAction(Animate.action(SeQuemo));
			}
			if (AlimentoAVerificar.getPositionY() < Limite2)
			{
			   ArrayComida.remove(AlimentoAVerificar);
			   CantIngredientesDesperdiciados++;
			   LabelCantIngredientesDesperdiciados.setString("Ingredientes desperdiciados: " + CantIngredientesDesperdiciados);
			   super.removeChild(AlimentoAVerificar, true);
			}
		 }
	  }
	  
	  boolean InterseccionEntreSprites(Sprite Sprite1, Sprite Sprite2)
	  {
		 int Sprite1Izquierda, Sprite1Derecha, Sprite1Arriba, Sprite1Abajo;
		 int Sprite2Izquierda, Sprite2Derecha, Sprite2Arriba, Sprite2Abajo;
		 
		 Sprite1Izquierda = (int) (Sprite1.getPositionX() - Sprite1.getWidth() / 2);
		 Sprite1Derecha = (int) (Sprite1.getPositionX() + Sprite1.getWidth() / 2);
		 Sprite1Abajo = (int) (Sprite1.getPositionY() - Sprite1.getHeight() / 2);
		 Sprite1Arriba = (int) (Sprite1.getPositionY() + Sprite1.getHeight() / 2);
		 
		 Sprite2Izquierda = (int) (Sprite2.getPositionX() - Sprite2.getWidth() / 2);
		 Sprite2Derecha = (int) (Sprite2.getPositionX() + Sprite2.getWidth() / 2);
		 Sprite2Abajo = (int) (Sprite2.getPositionY() - Sprite2.getHeight() / 2);
		 Sprite2Arriba = (int) (Sprite2.getPositionY() + Sprite2.getHeight() / 2);
		 
		 if (EstaEntre(Sprite1Izquierda, Sprite2Izquierda, Sprite2Derecha) &&
				  EstaEntre(Sprite1Abajo, Sprite2Arriba, Sprite2Abajo))
		 {
			//Borde inferior izquierdo del Sprite1, dentro del Sprite2
			return true;
		 }
		 if (EstaEntre(Sprite1Izquierda, Sprite2Izquierda, Sprite2Derecha) &&
				  EstaEntre(Sprite1Arriba, Sprite2Abajo, Sprite2Arriba))
		 {
			//Borde superior izquierdo del Sprite1, dentro del Sprite2
			return true;
		 }
		 if (EstaEntre(Sprite1Derecha, Sprite2Izquierda, Sprite2Derecha) &&
				  EstaEntre(Sprite1Abajo, Sprite2Abajo, Sprite2Arriba))
		 {
			//Borde inferior derecho del Sprite1, dentro del Sprite2
			return true;
		 }
		 if (EstaEntre(Sprite1Derecha, Sprite2Izquierda, Sprite2Derecha) &&
				  EstaEntre(Sprite1Arriba, Sprite2Abajo, Sprite2Arriba))
		 {
			//Borde superior derecho del Sprite1, dentro del Sprite2
			return true;
		 }
   
	  /*----YA SIENTO EL CAMBIO----*/
		 
		 if (EstaEntre(Sprite2Izquierda, Sprite1Izquierda, Sprite1Derecha) &&
				  EstaEntre(Sprite2Abajo, Sprite1Arriba, Sprite1Abajo))
		 {
			//Borde inferior izquierdo del Sprite2, dentro del Sprite1
			return true;
		 }
		 if (EstaEntre(Sprite2Izquierda, Sprite1Izquierda, Sprite1Derecha) &&
				  EstaEntre(Sprite2Arriba, Sprite1Abajo, Sprite1Arriba))
		 {
			//Borde superior izquierdo del Sprite2, dentro del Sprite1
			return true;
		 }
		 if (EstaEntre(Sprite2Derecha, Sprite1Izquierda, Sprite1Derecha) &&
				  EstaEntre(Sprite2Abajo, Sprite1Abajo, Sprite1Arriba))
		 {
			//Borde inferior derecho del Sprite2, dentro del Sprite1
			return true;
		 }
		 if (EstaEntre(Sprite2Derecha, Sprite1Izquierda, Sprite1Derecha) &&
				  EstaEntre(Sprite2Arriba, Sprite1Abajo, Sprite1Arriba))
		 {
			//Borde superior derecho del Sprite2, dentro del Sprite1
			return true;
		 }
		 
		 else
		 {
			return false;
		 }
	  }
	  
	  @Override
	  public boolean ccTouchesEnded(MotionEvent event)
	  {
		 Log.d("Toque termina", "X: " + event.getX() + " - Y: " + event.getY());
		 CocineroToqueteado = false;
		 return true;
	  }
	  
   }
   
   class CapaPerdiste extends Layer
   {
	  public CapaPerdiste()
	  {
		 PonerFondo();
		 PonerLabels();
		 PonerBotonReiniciar("botonreiniciar.png");
		 MediaPlayer Mammamia = MediaPlayer.create(_ContextoDelJuego, R.raw.mammamia);
		 Mammamia.start();
		 this.setIsTouchEnabled(true);
	  }
	  
	  private void PonerFondo()
	  {
		 Fondo = Sprite.sprite("fondoperdiste.png");
		 Float FactorAncho, FactorAlto;
		 FactorAncho = PantallaDelDevice.width / Fondo.getWidth();
		 FactorAlto = PantallaDelDevice.height / Fondo.getHeight();
		 
		 Fondo.setPosition(PantallaDelDevice.width / 2, PantallaDelDevice.height / 2);
		 Fondo.runAction(ScaleBy.action(0.01f, FactorAncho, FactorAlto));
		 super.addChild(Fondo);
	  }
   
	  public void PonerLabels()
	  {
		 LabelCantIngredientesUsados = Label.label("Ingredientes usados: " + (CantIngredientesHamburguesa +1), "Verdana", 30);
		 LabelCantIngredientesDesperdiciados = Label.label("Ingredientes desperdiciados: " + CantIngredientesDesperdiciados, "Verdana", 30);
	  
		 float AnchoDelLabelUsados, AnchoDelLabelDesperdiciados, AltoDelLabelUsados, AltoDelLabelDesperdiciados;
		 AnchoDelLabelUsados = LabelCantIngredientesUsados.getWidth();
		 AnchoDelLabelDesperdiciados = LabelCantIngredientesDesperdiciados.getWidth();
		 AltoDelLabelUsados = LabelCantIngredientesUsados.getHeight();
		 AnchoDelLabelDesperdiciados = LabelCantIngredientesDesperdiciados.getHeight();
	  
		 LabelCantIngredientesUsados.setPosition(PantallaDelDevice.width / 2, AltoDelLabelUsados+35);
		 LabelCantIngredientesDesperdiciados.setPosition(PantallaDelDevice.width / 2, AltoDelLabelUsados);
	  
		 CCColor3B Negro = new CCColor3B(0,0,0);
		 LabelCantIngredientesUsados.setColor(Negro);
		 LabelCantIngredientesDesperdiciados.setColor(Negro);
	  
		 super.addChild(LabelCantIngredientesUsados, 12);
		 super.addChild(LabelCantIngredientesDesperdiciados, 12);
	  
	  }
	  
	  private void PonerBotonReiniciar(String Imagen)
	  {
		 BotonReiniciar = Sprite.sprite(Imagen);
		 BotonReiniciar.setPosition(PantallaDelDevice.getWidth() / 2, PantallaDelDevice.height / 2 + 50);
		 super.addChild(BotonReiniciar);
	  }
	  
	  @Override
	  public boolean ccTouchesBegan(MotionEvent event)
	  {
		 if (EstaEntre((int) event.getX(),
				  (int) (BotonReiniciar.getPositionX() - BotonReiniciar.getWidth() / 2),
				  (int) (BotonReiniciar.getPositionX() + BotonReiniciar.getWidth() / 2)))
		 {
			if (EstaEntre((int) (PantallaDelDevice.getHeight() - event.getY() - BotonReiniciar.getHeight() / 2),
					 (int) (BotonReiniciar.getPositionY() - BotonReiniciar.getHeight() / 2),
					 (int) (BotonReiniciar.getPositionY() + BotonReiniciar.getHeight() / 2)))
			{
			   BotonReinicioToqueteado = true;
			}
		 }
		 if (BotonReinicioToqueteado)
		 {
			Director.sharedDirector().replaceScene(EscenaDelJuego());
		 }
		 return true;
	  }
   }
   
   class CapaGanaste extends Layer
   {
	  public CapaGanaste()
	  {
		 PonerFondo();
		 PonerLabels();
		 PonerBotonReiniciar("botonreiniciar.png");
		 MediaPlayer Wupi = MediaPlayer.create(_ContextoDelJuego, R.raw.wupi);
		 Wupi.start();
		 this.setIsTouchEnabled(true);
	  }
	  
	  private void PonerFondo()
	  {
		 Fondo = Sprite.sprite("fondoganaste.png");
		 Float FactorAncho, FactorAlto;
		 FactorAncho = PantallaDelDevice.width / Fondo.getWidth();
		 FactorAlto = PantallaDelDevice.height / Fondo.getHeight();
		 
		 Fondo.setPosition(PantallaDelDevice.width / 2, PantallaDelDevice.height / 2);
		 Fondo.runAction(ScaleBy.action(0.01f, FactorAncho, FactorAlto));
		 super.addChild(Fondo);
	  }
	  
	  private void PonerBotonReiniciar(String Imagen)
	  {
		 BotonReiniciar = Sprite.sprite(Imagen);
		 BotonReiniciar.setPosition(PantallaDelDevice.getWidth() / 2, PantallaDelDevice.height / 2 + 50);
		 super.addChild(BotonReiniciar);
	  }
	  
	  public void PonerLabels()
	  {
		 LabelCantIngredientesUsados = Label.label("Ingredientes usados: " + (CantIngredientesHamburguesa +1), "Verdana", 30);
		 LabelCantIngredientesDesperdiciados = Label.label("Ingredientes desperdiciados: " + CantIngredientesDesperdiciados, "Verdana", 30);
	  
		 float AnchoDelLabelUsados, AnchoDelLabelDesperdiciados, AltoDelLabelUsados, AltoDelLabelDesperdiciados;
		 AnchoDelLabelUsados = LabelCantIngredientesUsados.getWidth();
		 AnchoDelLabelDesperdiciados = LabelCantIngredientesDesperdiciados.getWidth();
		 AltoDelLabelUsados = LabelCantIngredientesUsados.getHeight();
		 AnchoDelLabelDesperdiciados = LabelCantIngredientesDesperdiciados.getHeight();
	  
		 LabelCantIngredientesUsados.setPosition(PantallaDelDevice.width / 2, AltoDelLabelUsados+35);
		 LabelCantIngredientesDesperdiciados.setPosition(PantallaDelDevice.width / 2, AltoDelLabelUsados);
	  
		 CCColor3B Negro = new CCColor3B(0,0,0);
		 LabelCantIngredientesUsados.setColor(Negro);
		 LabelCantIngredientesDesperdiciados.setColor(Negro);
	  
		 super.addChild(LabelCantIngredientesUsados, 12);
		 super.addChild(LabelCantIngredientesDesperdiciados, 12);
	  }
	  
	  @Override
	  public boolean ccTouchesBegan(MotionEvent event)
	  {
		 if (EstaEntre((int) event.getX(),
				  (int) (BotonReiniciar.getPositionX() - BotonReiniciar.getWidth() / 2),
				  (int) (BotonReiniciar.getPositionX() + BotonReiniciar.getWidth() / 2)))
		 {
			if (EstaEntre((int) (PantallaDelDevice.getHeight() - event.getY() - BotonReiniciar.getHeight() / 2),
					 (int) (BotonReiniciar.getPositionY() - BotonReiniciar.getHeight() / 2),
					 (int) (BotonReiniciar.getPositionY() + BotonReiniciar.getHeight() / 2)))
			{
			   BotonReinicioToqueteado = true;
			}
		 }
		 if (BotonReinicioToqueteado)
		 {
			Director.sharedDirector().replaceScene(EscenaDelJuego());
		 }
		 return true;
	  }
   }
   
   boolean EstaEntre(int NumeroAComparar, int NumeroMenor, int NumeroMayor)
   {
	  if (NumeroMenor > NumeroMayor)
	  {
		 int Auxiliar = NumeroMayor;
		 NumeroMayor = NumeroMenor;
		 NumeroMenor = Auxiliar;
	  }
	  if (NumeroAComparar >= NumeroMenor && NumeroAComparar <= NumeroMayor)
	  {
		 return true;
	  }
	  else
	  {
		 return false;
	  }
   }
}
