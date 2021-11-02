
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Math;

public class PereteInvizibil extends Perete {

    private String marime;
    private String pozitie;
    HashMap<String, GreenfootImage> directie = new HashMap<String, GreenfootImage>();
    GreenfootImage pereteImg;

    public PereteInvizibil(String pozitie, String marime) {

        directie.put("mic90", new GreenfootImage("perete/pereteInviz_mic90.png"));
        directie.put("mic", new GreenfootImage("perete/pereteInviz_mic.png"));
        directie.put("mare90", new GreenfootImage("perete/pereteInviz_mare90.png"));
        directie.put("mare", new GreenfootImage("perete/pereteInviz_mare.png"));

        this.marime = marime;
        pereteImg = directie.get(marime);
        setImage(pereteImg);
        this.pozitie = pozitie;

    }

    private void chestie() {
        int procentX = 85;
        int procentY = 90;
        switch (pozitie) {
            case ("W"): {
                if (marime.equals("mic90") || marime.equals("mare90")) {
                    int a = getImage().getWidth() / 2;

                    for (int i = 0; i <= a; i += 10) {

                        Actor b = getOneObjectAtOffset(i, 0, Jucator.class);
                        if (b != null) {
                            int dif = b.getY() - getY();
                            int k = getImage().getHeight() / 2 - dif;
                            if (k <= 0) {
                                k = k * (-1) + getImage().getHeight() / 2;
                            }
                            k -= k * procentY / 100;
                            b.setLocation(b.getX(), b.getY() + k);
                        }
                    }

                    for (int j = 10; j <= a; j += 10) {
                        Actor b = getOneObjectAtOffset(-j, 0, Jucator.class);
                        if (b != null) {
                            int dif = b.getY() - getY();
                            int k = getImage().getHeight() / 2 - dif;
                            if (k <= 0) {
                                k = k * (-1) + getImage().getHeight() / 2;
                            }
                            k -= k * procentY / 100;
                            b.setLocation(b.getX(), b.getY() + k);
                        }

                    }
                } else {
                    int a = getImage().getWidth() / 2;

                    for (int i = 0; i <= a; i += 20) {

                        Actor b = getOneObjectAtOffset(i, 0, Jucator.class);

                        if (b != null) {
                            int dif = b.getY() - getY();

                            int k = getImage().getHeight() / 2 - dif;
                            if (k <= 0) {
                                k = k * (-1) + getImage().getHeight() / 2;
                            }
                            k -= k * procentY / 100;
                            b.setLocation(b.getX(), b.getY() + k);
                        }
                    }

                    for (int j = 0; j <= a; j += 20) {
                        Actor b = getOneObjectAtOffset(-j, 0, Jucator.class);

                        if (b != null) {
                            int dif = b.getY() - getY();

                            int k = getImage().getHeight() / 2 - dif;
                            if (k <= 0) {
                                k = k * (-1) + getImage().getHeight() / 2;
                            }
                            k -= k * procentY / 100;
                            b.setLocation(b.getX(), b.getY() + k);
                        }
                    }
                }

                break;
            }

            case ("S"): {
                //dif=b.getY()-getY()-i
                if (marime.equals("mic90") || marime.equals("mare90")) {
                    int a = getImage().getWidth() / 2;

                    for (int i = 0; i <= a; i += 10) {

                        Actor b = getOneObjectAtOffset(i, 0, Jucator.class);
                        if (b != null) {
                            int dif = getY() - b.getY();

                            int k = getImage().getHeight() / 2 - dif;
                            if (k <= 0) {
                                k = k * (-1) + getImage().getHeight() / 2;
                            }
                            k -= k * procentY / 100;
                            b.setLocation(b.getX(), b.getY() - k);
                        }
                    }

                    for (int j = 10; j <= a; j += 10) {
                        Actor b = getOneObjectAtOffset(-j, 0, Jucator.class);
                        if (b != null) {
                            int dif = getY() - b.getY();
                            int k = getImage().getHeight() / 2 - dif;
                            if (k <= 0) {
                                k = k * (-1) + getImage().getHeight() / 2;
                            }
                            k -= k * procentY / 100;
                            b.setLocation(b.getX(), b.getY() - k);
                        }

                    }
                } else {
                    int a = getImage().getWidth() / 2;
                    for (int i = 0; i <= a; i += 20) {

                        Actor b = getOneObjectAtOffset(i, 0, Jucator.class);
                        if (b != null) {
                            int dif = getY() - b.getY();
                            int k = getImage().getHeight() / 2 - dif;
                            if (k <= 0) {
                                k = k * (-1) + getImage().getHeight() / 2;
                            }
                            k -= k * procentY / 100;
                            b.setLocation(b.getX(), b.getY() - k);
                        }
                    }

                    for (int j = 0; j <= a; j += 20) {
                        Actor b = getOneObjectAtOffset(-j, 0, Jucator.class);
                        if (b != null) {
                            int dif = getY() - b.getY();
                            int k = getImage().getHeight() / 2 - dif;
                            if (k <= 0) {
                                k = k * (-1) + getImage().getHeight() / 2;
                            }
                            k -= k * procentY / 100;
                            b.setLocation(b.getX(), b.getY() - k);
                        }

                    }
                }
                break;
            }

            case ("D"): {
                if (marime.equals("mic90") || marime.equals("mare90")) {
                    int a = getImage().getHeight() / 2;

                    for (int i = 0; i <= a; i += 10) {

                        Actor b = getOneObjectAtOffset(0, i, Jucator.class);
                        if (b != null) {
                            int dif = getX() - b.getX();

                            int k = getImage().getWidth() / 2 - dif;
                            if (k <= 0) {
                                k = k * (-1) + getImage().getWidth() / 2;
                            }
                            k -= k * procentX / 100;
                            b.setLocation(b.getX() - k, b.getY());
                        }
                    }

                    for (int j = 0; j <= a; j += 10) {
                        Actor b = getOneObjectAtOffset(0, -j, Jucator.class);
                        if (b != null) {
                            int dif = getX() - b.getX();
                            int k = getImage().getWidth() / 2 - dif;
                            if (k <= 0) {
                                k = k * (-1) + getImage().getWidth() / 2;
                            }
                            k -= k * procentX / 100;
                            b.setLocation(b.getX() - k, b.getY());
                        }

                    }
                } else {
                    int a = getImage().getHeight() / 2;
                    for (int i = 0; i <= a; i += 20) {

                        Actor b = getOneObjectAtOffset(0, i, Jucator.class);
                        if (b != null) {
                            int dif = getX() - b.getX();
                            int k = getImage().getHeight() / 2 - dif;
                            if (k <= 0) {
                                k = k * (-1) + getImage().getWidth() / 2;
                            }
                            k -= k * procentX / 100;
                            b.setLocation(b.getX() - k, b.getY());
                        }
                    }

                    for (int j = 0; j <= a; j += 20) {
                        Actor b = getOneObjectAtOffset(0, -j, Jucator.class);
                        if (b != null) {
                            int dif = getX() - b.getX();
                            int k = getImage().getHeight() / 2 - dif;
                            if (k <= 0) {
                                k = k * (-1) + getImage().getWidth() / 2;
                            }
                            k -= k * procentX / 100;
                            b.setLocation(b.getX() - k, b.getY());
                        }

                    }
                }
                break;
            }

            case ("A"): {
                if (marime.equals("mic90") || marime.equals("mare90")) {
                    int a = getImage().getHeight() / 2;

                    for (int i = 0; i <= a; i += 10) {

                        Actor b = getOneObjectAtOffset(0, i, Jucator.class);
                        if (b != null) {
                            int dif = b.getX() - getX();

                            int k = getImage().getWidth() / 2 - dif;
                            if (k <= 0) {
                                k = k * (-1) + getImage().getWidth() / 2;
                            }
                            k -= k * procentX / 100;
                            b.setLocation(b.getX() + k, b.getY());
                        }
                    }

                    for (int j = 0; j <= a; j += 10) {
                        Actor b = getOneObjectAtOffset(0, -j, Jucator.class);
                        if (b != null) {
                            int dif = b.getX() - getX();
                            int k = getImage().getWidth() / 2 - dif;
                            if (k <= 0) {
                                k = k * (-1) + getImage().getWidth() / 2;
                            }
                            k -= k * procentX / 100;
                            b.setLocation(b.getX() + k, b.getY());
                        }

                    }
                } else {
                    int a = getImage().getHeight() / 2;
                    for (int i = 0; i <= a; i += 20) {

                        Actor b = getOneObjectAtOffset(0, i, Jucator.class);
                        if (b != null) {
                            int dif = b.getX() - getX();
                            int k = getImage().getHeight() / 2 - dif;
                            if (k <= 0) {
                                k = k * (-1) + getImage().getWidth() / 2;
                            }
                            k -= k * procentX / 100;
                            b.setLocation(b.getX() + k, b.getY());
                        }
                    }

                    for (int j = 0; j <= a; j += 20) {
                        Actor b = getOneObjectAtOffset(0, -j, Jucator.class);
                        if (b != null) {
                            int dif = b.getX() - getX();
                            int k = getImage().getHeight() / 2 - dif;
                            if (k <= 0) {
                                k = k * (-1) + getImage().getWidth() / 2;
                            }
                            k -= k * procentX / 100;
                            b.setLocation(b.getX() + k, b.getY());
                        }
                    }
                }
                break;
            }
        }
    }

    public void act() {

        if (super.isInScreen()) {
            chestie();
        }

    }

}
