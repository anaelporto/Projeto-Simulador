/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.simulador_v2;

import java.util.Scanner;

/**
 *
 * @author anael
 */
public class Simulador_v2 {

        public static void main(String[] args) {
        long m, xn, xi;
        double xim, va = 15, vb = 19, xb, s1, s2, ic;
        double tec, ts, tcr, tis, tfs, tnf, tns, tcl, tme, tms, tmd, pcl;
        double stme1, stms1, stmd1;
        double[] stme2 = new double[130];
        double[] stms2 = new double[130];
        double[] stmd2 = new double[130];
        
        int a, c, i = 1, n, li, ls, fila = 1, semente;
        int cont = 0, continuar = 1, rodadas = 0;
        
        //Atribuição de valores iniciais
        n = 8000;
        xi = 10;
        
        a = 52489;
        c = 355;
        m = 2147483647;
        
        s1 = 0;
        
        li = 0;
        ls = 0;
        
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
        
        stme1 = 0;
        stms1 = 0;
        stmd1 = 0;
        
        //Preparando o Scanner pra ler os dados
        Scanner ler = new Scanner(System.in);
        
        //Separando o processo em 3 filas
        while(fila <= 3){
            switch(fila){
                case 1:
                    li = 3450;
                    ls = 3580;
                case 2:
                    li = 5110;
                    ls = 5240;
                case 3:
                    li = 7870;
                    ls = 8000;
            }
            
            //Simulando N vezes até o usuário desistir
            do{
                i = 1;
                xi = 10;
                pcl = 0;
                
                //Solicita que o usuário selecione uma semente
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
                
                //Gerando 8000 valores pseudoaleatórios
                while(i <= n){
                    xn = ((a * xi) + c) % m;

                    xim = ((double)xi)/((double)m);                
                    
                    //Selecionando 130 valores para aplicar os cálculos da fila
                    if(i > li && i < ls){

                        tec = (-10) * (Math.log(xim));

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
                
                //Após coletar os dados, aplicam-se algumas normalizações
                tme = tme / 130;
                stme2[rodadas] = tme;

                tms = tms / 130;
                stms2[rodadas] = tms;

                tmd = tmd / 130;
                stmd2[rodadas] = tmd;

                pcl = pcl / tfs;

                pcl = pcl / tmd;

                rodadas++;
                
                //Exibindo os dados simulados sobre cada rodada
                System.out.println(rodadas + "ª execução: ");
                System.out.println("Tempo médio de espera na fila: " + tme);
                System.out.println("Tempo médio de serviço: " + tms);
                System.out.println("Tempo médio despendido no sistema: " + tmd);
                System.out.println("Probabilidade de encontrar o caixa livre: " + pcl);
                
                //Acumulando dados das médias de N rodadas
                stme1 += tme;
                stms1 += tms;
                stmd1 += tmd;
                
                //Questionando o usuário para continuar ou não
                System.out.println("Continuar? 1 = S, 2 = N");
                continuar = ler.nextInt();
            }while(continuar == 1);
            
            //Exibindo os dados processados sobre as médias da fila
            System.out.println("Dados da " + fila + "ª fila:");
            
            System.out.println("Dados do tempo médio de espera na fila: ");

            xb = stme1 / rodadas;
            System.out.println("Média das médias: " + xb);

            while(cont < rodadas){
                s1 += Math.pow(stme2[cont] - xb, 2);
                cont++;
            }

            s2 = Math.sqrt(s1 / (rodadas - 1));

            System.out.println("Desvio padrão das médias: " + s2);

            System.out.println("Intervalo de confiança do Tempo médio na fila: ");

            ic = xb + 1.96 * (s2/Math.sqrt(rodadas));
            System.out.println("xb + (1.96 * s/√n): " + ic);
            ic = xb - 1.96 * (s2/Math.sqrt(rodadas));
            System.out.println("xb - (1.96 * s/√n): " + ic);

            System.out.println("Dados do tempo médio de serviço: ");
            s1 = 0;
            cont = 0;

            xb = stms1 / rodadas;
            System.out.println("Média das médias: " + xb);

            while(cont < rodadas){
                s1 += Math.pow(stms2[cont] - xb, 2);    
                cont++;
            }

            s2 = Math.sqrt(s1 / (rodadas - 1));

            System.out.println("Desvio padrão das médias: " + s2);

            System.out.println("Intervalo de confiança do Tempo médio no sistema: ");

            ic = xb + 1.96 * (s2/Math.sqrt(rodadas));
            System.out.println("xb + (1.96 * s/√n): " + ic);
            ic = xb - 1.96 * (s2/Math.sqrt(rodadas));
            System.out.println("xb - (1.96 * s/√n): " + ic);

            System.out.println("Dados do tempo médio despendido no sistema: ");
            s1 = 0;
            cont = 0;

            xb = stmd1 / rodadas;
            System.out.println("Média das médias: " + xb);

            while(cont < rodadas){
                s1 += Math.pow(stmd2[cont] - xb, 2);
                cont++;
            }

            s2 = Math.sqrt(s1 / (rodadas - 1));

            System.out.println("Desvio padrão das médias: " + s2);

            System.out.println("Intervalo de confiança do Tempo médio de serviço: ");

            ic = xb + 1.96 * (s2/Math.sqrt(rodadas));
            System.out.println("xb + (1.96 * s/√n): " + ic);
            ic = xb - 1.96 * (s2/Math.sqrt(rodadas));
            System.out.println("xb - (1.96 * s/√n): " + ic);
            
            fila++;
        }
    }    
}
