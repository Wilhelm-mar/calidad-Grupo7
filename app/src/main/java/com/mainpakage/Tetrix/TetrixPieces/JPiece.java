package com.mainpakage.Tetrix.TetrixPieces;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

public class JPiece implements TetrixPiece {

        private int status=0;
        private CubeSprite[] cubes;
        private final int xIni;
        private final int yIni;
        private final int spriteLength;
        private final int interpieceSpace;

        public JPiece(Bitmap bmp, View view){
            CubeSprite aux = new CubeSprite(bmp, view);
            xIni=aux.getLength()*3;
            yIni=0;
            spriteLength=bmp.getWidth();
            interpieceSpace=0;
            cubes = new CubeSprite[4];

            for(int i=0;i<4;i++){
                cubes[i]= new CubeSprite(bmp,view);
            }

            cubes[0].setX(xIni);
            cubes[0].setY(yIni);
            cubes[1].setX(xIni);
            cubes[1].setY(yIni+spriteLength+interpieceSpace);
            cubes[2].setX(xIni);
            cubes[2].setY(yIni+(spriteLength+interpieceSpace)*2);
            cubes[3].setX(xIni-spriteLength-interpieceSpace);
            cubes[3].setY(yIni+(spriteLength+interpieceSpace)*2);
            this.rotate90Right();

        }

    public JPiece(Bitmap bmp,View view,int xini,int yini){
        xIni=xini;
        yIni=yini;
        spriteLength=bmp.getWidth();
        interpieceSpace=0;
        cubes = new CubeSprite[4];

        for(int i=0;i<4;i++){
            cubes[i]= new CubeSprite(bmp,view);
        }

        cubes[0].setX(xIni);
        cubes[0].setY(yIni);
        cubes[1].setX(xIni);
        cubes[1].setY(yIni+spriteLength+interpieceSpace);
        cubes[2].setX(xIni);
        cubes[2].setY(yIni+(spriteLength+interpieceSpace)*2);
        cubes[3].setX(xIni-spriteLength-interpieceSpace);
        cubes[3].setY(yIni+(spriteLength+interpieceSpace)*2);
        this.rotate90Right();

    }

        private void changeStatus(){
            if (this.status <3){
                this.status++;
            }
            else{
                this.status=0;
            }
        }


    @Override
        public void rotate90Right() {
            if(this.status==0){
                cubes[0].setX(cubes[1].getX()+spriteLength+interpieceSpace);
                cubes[0].setY((cubes[1].getY()));
                cubes[2].setX(cubes[1].getX()-spriteLength-interpieceSpace);
                cubes[2].setY(cubes[1].getY());
                cubes[3].setX(cubes[2].getX());
                cubes[3].setY(cubes[1].getY()-spriteLength-interpieceSpace);
            }
            else if(this.status==1){
                cubes[0].setX(cubes[1].getX());
                cubes[0].setY((cubes[1].getY())+spriteLength+interpieceSpace);
                cubes[2].setX(cubes[1].getX());
                cubes[2].setY(cubes[1].getY()-spriteLength-interpieceSpace);
                cubes[3].setX(cubes[2].getX()+spriteLength+interpieceSpace);
                cubes[3].setY(cubes[2].getY());
            }
            else if(this.status==2){
                cubes[0].setX(cubes[1].getX()-spriteLength-interpieceSpace);
                cubes[0].setY((cubes[1].getY()));
                cubes[2].setX(cubes[1].getX()+spriteLength+interpieceSpace);
                cubes[2].setY(cubes[1].getY());
                cubes[3].setX(cubes[2].getX());
                cubes[3].setY(cubes[2].getY()+spriteLength+interpieceSpace);
            }
            else{
                cubes[0].setX(cubes[1].getX());
                cubes[0].setY((cubes[1].getY())-spriteLength-interpieceSpace);
                cubes[2].setX(cubes[1].getX());
                cubes[2].setY(cubes[1].getY()+spriteLength+interpieceSpace);
                cubes[3].setX(cubes[1].getX()-spriteLength-interpieceSpace);
                cubes[3].setY(cubes[2].getY());
            }
            changeStatus();
        }


        @Override
        public void changeYSpeed(int speed) {
            for(int i=0;i<4;i++){
                if(cubes[i]!=null)
                    cubes[i].setySpeed(speed);
            }
        }

        @Override
        public boolean removeCube(int y) {
            boolean aux=false;
            for(int i=0;i<4;i++){
                if(cubes[i]!=null&&cubes[i].getY()==y){
                    cubes[i]=null;
                    aux=true;
                }
            }
            return aux;
        }

        @Override
        public void onDraw(Canvas canvas) {
            for(int i=0;i<4;i++){
                if(cubes[i]!=null)
                    cubes[i].onDraw(canvas);
            }
        }

        @Override
        public CubeSprite[] getSprites() {
            return cubes;
        }

        @Override
        public int getInterSpace() {
            return this.interpieceSpace;
        }

    @Override
    public void update() {
        for(int i=0;i<4;i++){
            if(cubes[i]!=null)
                cubes[i].update();
        }
    }

    public TetrixPiece copyRight(Bitmap bmp,View view){
        CubePiece nuevo = new CubePiece(bmp, view);
        CubeSprite[] c =nuevo.getSprites();
        c[0].setX(cubes[0].getX()+spriteLength);
        c[0].setY(cubes[0].getY());
        c[1].setX(cubes[1].getX()+spriteLength);
        c[1].setY(cubes[1].getY());
        c[2].setX(cubes[2].getX()+spriteLength);
        c[2].setY(cubes[2].getY());
        c[3].setX(cubes[3].getX()+spriteLength);
        c[3].setY(cubes[3].getY());

        return nuevo;
    }

    public void moveRight() {
        for (int i=0; i<4; i++) {
            cubes[i].setX(cubes[i].getX()+spriteLength);
        }
    }
    public TetrixPiece copyLeft(Bitmap bmp,View view){
        CubePiece nuevo = new CubePiece(bmp, view);
        CubeSprite[] c =nuevo.getSprites();
        c[0].setX(cubes[0].getX()-spriteLength);
        c[0].setY(cubes[0].getY());
        c[1].setX(cubes[1].getX()-spriteLength);
        c[1].setY(cubes[1].getY());
        c[2].setX(cubes[2].getX()-spriteLength);
        c[2].setY(cubes[2].getY());
        c[3].setX(cubes[3].getX()-spriteLength);
        c[3].setY(cubes[3].getY());

        return nuevo;
    }

    public TetrixPiece copyDown(Bitmap bmp,View view){
        CubePiece nuevo = new CubePiece(bmp, view);
        CubeSprite[] c =nuevo.getSprites();
        c[0].setX(cubes[0].getX());
        c[0].setY(cubes[0].getY()+spriteLength);
        c[1].setX(cubes[1].getX());
        c[1].setY(cubes[1].getY()+spriteLength);
        c[2].setX(cubes[2].getX());
        c[2].setY(cubes[2].getY()+spriteLength);
        c[3].setX(cubes[3].getX());
        c[3].setY(cubes[3].getY()+spriteLength);

        return nuevo;
    }
    public void moveLeft() {
        for (int i=0; i<4; i++) {
            cubes[i].setX(cubes[i].getX()-spriteLength);
        }
    }

    public TetrixPiece copyRotate(Bitmap bmp,View view){
        JPiece nuevo = new JPiece(bmp, view);
        CubeSprite[] c =nuevo.getSprites();
        c[0].setX(cubes[0].getX());
        c[0].setY(cubes[0].getY());
        c[1].setX(cubes[1].getX());
        c[1].setY(cubes[1].getY());
        c[2].setX(cubes[2].getX());
        c[2].setY(cubes[2].getY());
        c[3].setX(cubes[3].getX());
        c[3].setY(cubes[3].getY());

        nuevo.setStatus(this.getStatus());
        nuevo.rotate90Right();
        return nuevo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setBitmap(Bitmap bitmap){
        for(int i=0;i<4;i++){
            if(cubes[i]!=null)
                cubes[i].setBmp(bitmap);
        }
    }

    @Override
    public int isPowerUp() {
        return 0;
    }
}
