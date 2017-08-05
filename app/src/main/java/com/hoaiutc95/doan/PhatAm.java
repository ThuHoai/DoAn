package com.hoaiutc95.doan;

import android.content.Context;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Thu Hoai on 5/12/2017.
 */

public class PhatAm  extends AppCompatActivity{
    public static int[] _fruit = new int[]{R.raw.quatao, R.raw.quachuoi,
            R.raw.quanho, R.raw.quaxoai, R.raw.quaduwa, R.raw.quadua, R.raw.quadautay, R.raw.quachanh,
            R.raw.quaot, R.raw.cachua, R.raw.quabo, R.raw.quacherry, R.raw.quaduachuot, R.raw.quale, R.raw.bapngo, R.raw.quamit, R.raw.quathanhlong,R.raw.quacam};


    public static int[] _alphabet = new int[]{R.raw.chu_a, R.raw.chu_aw, R.raw.aa, R.raw.chu_b, R.raw.chu_c, R.raw.d, R.raw.chu_dd,
            R.raw.chu_e, R.raw.e1, R.raw.g, R.raw.chu_h, R.raw.chu_i, R.raw.chu_k, R.raw.l, R.raw.m, R.raw.n, R.raw.o, R.raw.o1, R.raw.chu_ow,
            R.raw.chu_t, R.raw.chu_q, R.raw.chur, R.raw.chu_s, R.raw.chu_t, R.raw.chu_u, R.raw.uu, R.raw.chu_v, R.raw.chu_x, R.raw.chu_y};

    public static int[] _animal = new int[]{R.raw.conmeo, R.raw.conbo, R.raw.concho, R.raw.conlon, R.raw.congatrong, R.raw.conho, R.raw.consutu, R.raw.convet, R.raw.concumeo, R.raw.conngua, R.raw.conde, R.raw.conco, R.raw.concong, R.raw.congatay, R.raw.conran};
    public static int[] _music = new int[]{R.raw.tiengmeo, R.raw.tiengborong, R.raw.tiengcho, R.raw.tiengheo, R.raw.tiengga, R.raw.tiengho, R.raw.tiengsutu, R.raw.tiengvet, R.raw.tiengcumeo, R.raw.tiengngua};
    public static int[] _color = new int[]{R.raw.mautrang, R.raw.mauden, R.raw.maunau, R.raw.mauhong, R.raw.mautim, R.raw.mauvang, R.raw.maudo,
            R.raw.xanhlacay, R.raw.mauxanhduong, R.raw.mauxam, R.raw.maudacam};

    public  static int[] _number = new int[]{R.raw.so0,R.raw.so1,R.raw.so2,R.raw.so3,R.raw.so4,R.raw.so5,R.raw.so6,R.raw.so7,R.raw.so8,R.raw.so9,R.raw.so10};

    public static  int[] _shape = new int[]{R.raw.hinhvuong,R.raw.hinhtron,R.raw.hinhtamgiac,R.raw.hinhchunhat,R.raw.hinhelip,R.raw.hinhthoi,R.raw.tugiac};

    public static  int[] _device = new int[]{R.raw.oto,R.raw.quyensach,R.raw.tivi,R.raw.xedap,R.raw.xemay,R.raw.caithia,R.raw.caicoc,R.raw.caidia,R.raw.cibat,R.raw.caibanchaidanhrang,R.raw.caighe,R.raw.caiban,R.raw.caichao,R.raw.caixong,R.raw.caimu};

    public  static Integer[]  data = new Integer[]{R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f, R.drawable.g,
            R.drawable.h, R.drawable.i, R.drawable.j, R.drawable.k, R.drawable.l, R.drawable.m, R.drawable.n, R.drawable.o,
            R.drawable.p, R.drawable.q, R.drawable.so0, R.drawable.so1, R.drawable.so2, R.drawable.co3, R.drawable.so4, R.drawable.so5};








}
