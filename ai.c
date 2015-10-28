#include <stdio.h>
#include <math.h>
#include <string.h>

#define EU 2 //--id do jogador atual
#define DADOS 0 //n de dados do numero apostado do jogador atual
#define NJOG 2 //n de jogadores
#define DADOST 4 //n de dados total
//#define NA 1 //valor da aposta
//#define FD 3 //face da aposta

typedef struct jogadas {
  int nj;
  int nd;
  int na[16];
  int fd[16];
  int jog[16];
} jogadas;
//nj-num da jogada, nd-num dados, na-num da aposta, fd-face do dado, jog-id jogador

typedef struct jogador {
  double cj[8];
  int ndj[8];
} jogador;
// cj-cred jogador, ndj-num dados jogador 

int
main()
{
  //n-numero da jogada, d-num dados(sem proprios), sa-num aposta(sem proprios)
  int i,n,d,sa;
  // c-cred da aposta
  double c;
  jogadas j={7,DADOST,{1,1,2,2,3,3,4,1,1,5,0,0,0,0,0},{2,4,1,3,2,6,4,5,1,5,0,0,0,0,0},{2,3,4,1,2,3,4,1,2,3,0,0,0,0,0}};
  jogador l={{0.9,0.3,0.45,0.69,0,0,0},{2,2,2,2,0,0,0}};
  int dados[8]={0,1,0,0,1,0,0};
  n=j.nj;

  d=j.nd - l.ndj[EU];
  sa=j.na[n] - dados[j.fd[n]]; 
  printf("\n %d %d \n",j.na[n],j.fd[n]);
  if(sa<=0||sa<=0.17*d)
    {
      c=1;
    }
  else if(sa>d||(d>3&&sa>=d)||(d>5&&sa>=d-1)||(d>7&&sa>0.5*d))
    {
      c=0;
    }
  else if(d<=2&&NJOG<3)
    {
      //numeros mt mt pequenos 2jog
      
      if(sa==2)
	c=0.4*l.cj[j.jog[n]];
      else
	c=l.cj[j.jog[n]];
    }
  else if(sa<=4&&NJOG<3)
    {
      //numeros mt pequenos 2jog


    }
   else if(sa<=4)
    {
      //numeros mt pequenos +2jog
    }
  else if(NJOG<3)
    {
      //2 Jogadores
    }
  else if(sa<7&&NJOG>=3)
    {
      //numeros pequenos alguns jogadores
    }
  else
    {
      //outros
    }

  printf("%lf \n",c);
  
  return 0;
}
  

  
