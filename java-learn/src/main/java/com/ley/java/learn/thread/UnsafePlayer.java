package com.ley.java.learn.thread;

import java.lang.reflect.Field;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/4/30 20:43
 * @describe
 * @see Unsafe
 */
public class UnsafePlayer {
    public static void main(String[] args) throws Exception {
        //通过反射实例化Unsafe
        Field f = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        sun.misc.Unsafe unsafe = (sun.misc.Unsafe) f.get(null);
        //实例化Player
        Player player = (Player) unsafe.allocateInstance(Player.class);
        player.setName("li lei");
        System.out.println(player.getName());

    }
}

class Player {
    private String name;

    private Player() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}