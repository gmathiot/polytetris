/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

//import java.awt.Color;
import view.TetrisView;

/**
 *
 * @author logan
 */
public class Grille {
    public Integer tab[][];
    public int statPieces[];
    public int x;
    public int y;
    public Piece pieceDescente;
    public TetrisView view;
    public int pos;
    public Score score;
    public boolean termine;
    public Piece tabPieceSuivante[];
    public Piece pieceHold;
    public int level;
    
    public Grille(int x , int y, TetrisView view, Score score){
        this.x = x;
        this.y = y;
        this.level = 1;
        tab = new Integer[x][y];
        this.score = score;
        this.pieceHold = null;
        this.reinitialiserTableau();
        this.view = view;
        this.pos = 0;
        this.termine = false;
        this.statPieces = new int[] {0, 0, 0, 0, 0, 0, 0};
        randomTableauPiece();
    } 
    
    public void reinitialiserTableau()
    {
        score.score = 0;
        for(int i = 0; i < x; i ++){
            for(int j = 0; j < y; j++) {
                tab[i][j] = -1;
            }
        }
    }
    
    private void randomTableauPiece()
    {
        this.tabPieceSuivante = new Piece[3];
        for(int i = 0; i < 3; i++)
        {
            Piece piece = new PieceCarre();
            int pieceRandom = this.random();
            
            //un nombre aléatoire tiré va définir le type de pièce généré :
            switch(pieceRandom)
            {
                case 1 :
                    break;

                case 2 : piece = new PieceL();
                    break;

                case 3 : piece = new PieceLigne();
                    break;

                case 4 : piece = new PieceS();
                    break;

                case 5 : piece = new PieceT();
                    break;

                case 6 : piece = new PieceZ();
                    break;
                    
                case 7 : piece = new PieceJ();
                    break;
            }
            piece.x = 0;
            piece.y = 0;
            tabPieceSuivante[i] = piece;
        }
    }
    
    public void holdPiece() throws Exception
    {
        effacerPiece();
        Piece tmp = this.pieceDescente;
        if(this.pieceHold != null) //si il y a une pièce de retenue
        {
            this.pieceDescente = this.pieceHold; //la pièce actuelle devient la pièce retenue
            this.pieceDescente.x = tmp.x; //mais on garde les coordonnées de la pièce actuelle
            this.pieceDescente.y = tmp.y;
        }
        else
            addPiece(); //si il n'y a pas de pièce de retenue, la pièce actuelle est changée par la suivante dans la liste
        this.pieceHold = tmp; //et dans tous les cas, la pièce actuelle est retenue
    }
    
    public void update(){
        int masqueX = 0, masqueY = 0;
        for(int i = this.pieceDescente.x; i < this.pieceDescente.x + this.pieceDescente.largeur; i++){
            masqueY = 0;
            for(int j = this.pieceDescente.y; j < this.pieceDescente.y + this.pieceDescente.hauteur ; j++){
                
                if(this.pieceDescente.tab[this.pos][masqueX][masqueY])
                {
                    tab[i][j] = this.pieceDescente.type;
                }
                masqueY++; 
            }
            masqueX++;
        }
    }
    
    public void effacerPiece(){
        int masqueX = 0, masqueY = 0;
        for(int i = this.pieceDescente.x; i < this.pieceDescente.x + this.pieceDescente.largeur; i++){
            masqueY = 0;
            for(int j = this.pieceDescente.y; j < this.pieceDescente.y + this.pieceDescente.hauteur ; j++){
                
                if(this.pieceDescente.tab[this.pos][masqueX][masqueY])
                {
                    tab[i][j] = -1;
                }
                masqueY++;
            }
            masqueX++;
        }
    }
    
    public void effacerLigne(int ligne)
    {
        for(int i = 0; i < this.x; i++)
        {
            tab[i][ligne] = -1;
        }
        
        for(int i = 0; i < this.x; i++)
        {
            for(int j = ligne; j >= 0; j--)
            {
                if(this.tab[i][j] != -1)
                {
                    this.tab[i][j + 1] = this.tab[i][j];
                    this.tab[i][j] = -1;
                }
            }
        }
        //Son son = new Son("../sounds/2.wav");
        //son.play();
    }
    
    public void verifLigne()
    {
        boolean supprLigne;
        int nbLignes = 0;
        for(int i = 0; i < this.y; i++)
        {
            supprLigne = true;
            for(int j = 0; j < this.x; j++)
            {
                if(this.tab[j][i] == -1)
                {
                    supprLigne = false;
                    break;
                }
            }
            if(supprLigne)
            {
                effacerLigne(i);
                nbLignes++;
                this.score.nbLigneReussies++;
            }
        }

        this.score.incrementScore(nbLignes);
        this.score.actualiseLevel();
        this.view.displayScore(score);
    }
    
    private int random()
    {
        return (int)(Math.random() * (8-1)) + 1;
    }
    
    public void addPiece() throws Exception{
        
        boolean randomOk = false;
        int moyenneStatPieces = 0;
        
        for(int i = 0; i < statPieces.length; i++)
        {
            moyenneStatPieces += statPieces[i];
        }
        moyenneStatPieces = moyenneStatPieces/statPieces.length;
        
        this.verifLigne();
        
        this.pieceDescente = this.tabPieceSuivante[0];  
        double xDep = Math.ceil(this.x/2 - pieceDescente.largeur/2);//Largeur grille/2 - largeur piece/2 (piece centrée)
        pieceDescente.x = (int)xDep;
        pieceDescente.y = 0;
        this.pos = 0;
        
        Piece piece = new PieceCarre();
        int pieceRandom = 0;
        while(!randomOk){
                pieceRandom = this.random();
                if(statPieces[pieceRandom - 1] < moyenneStatPieces + 3){
                    randomOk = true;
                }    
            }
        statPieces[pieceRandom - 1]++;
                
        switch(pieceRandom)
        {
            case 1 :
                break;
                
            case 2 : piece = new PieceL();
                break;
                
            case 3 : piece = new PieceLigne();
                break;
                
            case 4 : piece = new PieceS();
                break;
                
            case 5 : piece = new PieceT();
                break;
                
            case 6 : piece = new PieceZ();
                break;
                
            case 7 : piece = new PieceJ();
                break;
        }
        
        this.tabPieceSuivante[0] = this.tabPieceSuivante[1];
        this.tabPieceSuivante[1] = this.tabPieceSuivante[2];
        piece.x = 0;
        piece.y = 0;
        this.tabPieceSuivante[2] = piece;
        
        this.update();
        if(this.collision("BAS"))
        {
            view.gameOver(this);
            this.termine = true;
            
        }
        this.view.display(this);
    }
    
    public void descendrePiece() throws Exception
    {
        if(!this.collision("BAS")){
            this.effacerPiece();
            this.pieceDescente.y += 1;
            this.update();
            this.view.display(this);
        }
        else
        {
            this.addPiece();
        }
        
    }
    
    public void decaleDroitePiece()
    {
        if(!this.collision("DROITE")){
            this.effacerPiece();
            this.pieceDescente.x += 1;
            this.update();
            this.view.display(this);
        }
               
    }
    
    public void decaleGauchePiece()
    {
        if(!this.collision("GAUCHE")){
            this.effacerPiece();
            this.pieceDescente.x -= 1;
            this.update();
            this.view.display(this);
        }
                
    }
    
    public void rotationPiece(){
        
        if(!(this.pieceDescente.x <= -1 || (this.pieceDescente.x + this.pieceDescente.largeur > this.x)))
        {
            if(!this.collision("BAS")){
                int temp = this.pos;
                this.effacerPiece();
                this.pos++;
                this.pos %= 4;
                if(!this.collisionRotation()){

                    this.update();
                    this.view.display(this);
                }
                else
                {
                    this.effacerPiece();
                    this.pos = temp;
                    this.update();
                }
            }
        }
    }
    
    public boolean collisionRotation(){
        int i, j;
        int masqueX = 0, masqueY = 0;
        
        for(i = this.pieceDescente.x - this.pieceDescente.decalageMasqueX; i < this.pieceDescente.x + this.pieceDescente.largeur; i++){
            masqueY = 0;
            for(j = this.pieceDescente.y - this.pieceDescente.decalageMasqueY; j < this.pieceDescente.y + this.pieceDescente.hauteur ; j++){
                if(this.tab[i][j] != -1 && this.pieceDescente.tab[this.pos][masqueX][masqueY]){
                    return true;                    
                }
                masqueY++;
            }
            masqueX++;
        }
        
        return false;
    }
    
    public boolean collision(String side){
        int i, j;
        int masqueX = 0, masqueY = 0;
        switch(side){
            case "BAS":
                for(i = this.pieceDescente.x; i < this.pieceDescente.x + this.pieceDescente.largeur; i++){
                    masqueY = 0;
                    for(j = this.pieceDescente.y; j < this.pieceDescente.y + this.pieceDescente.hauteur ; j++){

                        if(this.pieceDescente.tab[this.pos][masqueX][masqueY])
                        {
                            if(j < this.y-1 && masqueY < this.pieceDescente.hauteur)
                            {
                                try
                                {
                                    if(this.tab[i][j + 1] != -1 && !this.pieceDescente.tab[this.pos][masqueX][masqueY + 1])
                                        return true;
                                }
                                catch(ArrayIndexOutOfBoundsException ex)
                                {
                                    return true;
                                }
                            }
                            else if(j == this.y - 1){
                                return true;
                            }
                        }
                        masqueY++;
                    }
                    masqueX++;
                }
                
                break;
            case "GAUCHE":
                for(i = this.pieceDescente.x; i < this.pieceDescente.x + this.pieceDescente.largeur; i++){
                    masqueY = 0;
                    for(j = this.pieceDescente.y; j < this.pieceDescente.y + this.pieceDescente.hauteur ; j++){

                        if(this.pieceDescente.tab[this.pos][masqueX][masqueY])
                        {
                            if(i >= 0 && masqueX >= 0)
                            {
                                try
                                {
                                    if(this.tab[i - 1][j] != -1 && !this.pieceDescente.tab[this.pos][masqueX - 1][masqueY])
                                    {
                                        return true;
                                    }
                                }
                                catch(ArrayIndexOutOfBoundsException ex)
                                {
                                    return true;
                                }
                                
                            }
                            else if(i == 0){
                                return true;
                            }
                            
                        }
                        masqueY++;

                    }
                    masqueX++;
                }
                
                break;
            case "DROITE":
                for(i = this.pieceDescente.x; i < this.pieceDescente.x + this.pieceDescente.largeur; i++){
                    masqueY = 0;
                    for(j = this.pieceDescente.y; j < this.pieceDescente.y + this.pieceDescente.hauteur ; j++){

                        if(this.pieceDescente.tab[this.pos][masqueX][masqueY])
                        {
                            if(i < this.x-1 && masqueX < this.pieceDescente.largeur)
                            {
                                try
                                {
                                    if(this.tab[i+1][j] != -1 && !this.pieceDescente.tab[this.pos][masqueX+1][masqueY])
                                    {
                                        return true;
                                    }
                                }
                                catch(ArrayIndexOutOfBoundsException ex)
                                {
                                    return true;
                                }
                            }
                            else if(i == this.x - 1){
                                return true;
                            }
                        }
                        masqueY++;
                    }
                    masqueX++;
                }
                break;
        }
        return false;
    }
}