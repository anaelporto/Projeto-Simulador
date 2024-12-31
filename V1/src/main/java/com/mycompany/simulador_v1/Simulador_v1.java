package com.mycompany.simulador_v1;

import java.util.Scanner;
import java.lang.Math;

/**
 *
 * @author aluno
 */
public class Simulador_v1 {
    
    public static void main(String[] args) {
        long m, xn, xi;
        double xim, va, vb, xb, s, ic;
        double tec, ts, tcr, tis, tfs, tnf, tns, tcl;
        double tme, tms, tmd, pcl;
        double stme1, stms1, stmd1, stme2, stms2, stmd2;
        int a, c, i = 1, n, semente;
        int cont = 0, continuar = 1, rodadas = 0;
        
        n = 8000;
        xi = 10;
        
        a = 52489;
        c = 355;
        m = 2147483647;
        
        tec = 0;
        ts = 0;
        tcr = 0;
        tis = 0;
        tfs = 0;
        tnf = 0;
        tns = 0;
        tcl = 0;
        
        tme = 0;
        tms = 0;
        tmd = 0;
        pcl = 0;
        
        stme1 = 0;
        stms1 = 0;
        stmd1 = 0;
        stme2 = 0;
        stms2 = 0;
        stmd2 = 0;
        
        Scanner ler = new Scanner(System.in);
        
        while(continuar == 1){
            System.out.println("Escolha uma semente predefinida de 1 a 10: ");
            semente = ler.nextInt();
            
            switch(semente){
                case 1:
                    a = 52489;
                case 2:
                    a = 47591;
                case 3:
                    a = 63901;
                case 4:
                    a = 84377;
                case 5:
                    a = 97367;
                case 6:
                    a = 39869;
                case 7:
                    a = 72973;
                case 8:
                    a = 27361;
                case 9:
                    a = 15193;
                case 10:
                    a = 39659;                    
            }
            
            System.out.println("Insira a:");
            va = ler.nextInt();

            System.out.println("Insira b:");
            vb = ler.nextInt();

            //System.out.println("Cliente  TEC     TS      TCR     TIS     TFS     TNF     TNS     TCL ");
            
            while(i <= n){
                xn = ((a * xi) + c) % m;

                xim = ((double)xi)/((double)m);                

                if(i > 7900){
                    cont++;

                    tec = (-20) * (Math.log(xim));

                    ts = ((vb - va) * xim) + va;

                    tcr += tec;

                    if(tcr > tfs){
                        tis = tcr;
                    }
                    else{
                        tis = tfs;
                    } 

                    tcl = tis - tfs;

                    tfs = tis + ts;

                    tnf = tis - tcr;

                    tns = ts + tnf;
                    
                    tme += tnf;

                    tms += ts;

                    tmd += tns;

                    pcl += tcl;
                }

                xi = xn;
                i++;
            }

            tme = tme / 100;
            tms = tms / 100;

            tmd = tmd / 100;
            pcl = pcl / tfs;

            pcl = pcl / tmd;
            
            rodadas++;
            
            System.out.println(rodadas + "ª execução: ");
            System.out.println("Tempo médio de espera na fila: " + tme);
            System.out.println("Tempo médio de serviço: " + tms);
            System.out.println("Tempo médio despendido no sistema: " + tmd);
            System.out.println("Probabilidade de encontrar o caixa livre: " + pcl);
            
            stme1 += tme;
            stms1 += tms;
            stmd1 += tmd;
            stme2 += (tme * tme);
            stms2 += (tms * tms);
            stmd2 += (tmd * tmd);
            
            System.out.println("Continuar? 1 = S, 2 = N");
            continuar = ler.nextInt();
        }
        
        System.out.println("Dados do tempo médio de espera na fila: ");
        
        xb = stme1 / rodadas;
        System.out.println("Média das médias: " + xb);
        
        s = (1.0 / (rodadas - 1)) * ((stme2) - ((stme1 * stme1) / rodadas));
        System.out.println("Desvio padrão das médias: " + s);
        
        //ic = xb +   ;
        //System.out.println("Intervalo de confiança do Tempo médio na fila: " + ic);
        
        System.out.println("Dados do tempo médio de serviço: ");
        
        xb = stms1 / rodadas;
        System.out.println("Média das médias: " + xb);
        
        s = (1.0 / (rodadas - 1)) * ((stms2) - ((stms1 * stms1) / rodadas));
        System.out.println("Desvio padrão das médias: " + s);
        
        // ic = ;        
        //System.out.println("Intervalo de confiança do Tempo médio no sistema: " + ic);
        
        System.out.println("Dados do tempo médio despendido no sistema: ");
        
        xb = stmd1 / rodadas;
        System.out.println("Média das médias: " + xb);
        
        s = (1.0 / (rodadas - 1)) * ((stmd2) - ((stmd1 * stmd1) / rodadas));
        System.out.println("Desvio padrão das médias: " + s);
        
        //ic = ;
        // System.out.println("Intervalo de confiança do Tempo médio de serviço: " + ic);
    }
}
