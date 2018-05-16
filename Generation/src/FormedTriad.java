import java.util.ArrayList;

public class FormedTriad {
    private static ArrayList <String> beginList;
    private static ArrayList <String> polizeList;
    private static ArrayList <Triad> tableTriad = new ArrayList();
    private static int labelIndex = 0;
    private static String numLabelForDo = "";

    //Запуск считывания файла и формирования массива beginList
    public static void run() {
        try {
            beginList = Reader.run();
            System.out.println();
        } catch (Exception e) {
            System.out.println("Error reading the file!");
        }
        System.out.println("Array of input data: ");
        System.out.println(beginList);
        System.out.println();
    }

    public static void deleteBegin(){
        beginList.remove(beginList.get(0));
    }

    //формирует новый массив, каждый элемент которого, сформирован из считанного до символа(элемента) ";"
    public static ArrayList cutList() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < beginList.size(); i++) {
            if (beginList.get(i).equals(";")){ arrayList.add(beginList.get(i)); beginList.set(i," "); break;}
            else {
                arrayList.add(beginList.get(i));
                beginList.set(i," ");
            }
        }

        int k = 0;
        while(k != beginList.size()) {
            if (beginList.get(k).equals(" ")) beginList.remove(beginList.get(k));
            else k++;
        }
        return arrayList;
    }

    //Основной метод, выполняющий вызов методов данного класса для построения таблицы триад
    public static  ArrayList<Triad> runFormedTriad (){
        run();
        deleteBegin();

        for (int i = 0; i < beginList.size(); i++) {
            polizeList = cutList();
            ArrayList<String> alist = Polize.calcPoliz(polizeList);
            System.out.println(alist);
            formTriad(alist);
            alist.removeAll(alist);
            System.out.println();
        }

        System.out.println("Table of triad: ");
        tableTriad.forEach(e -> System.out.println("Triad " + e + " "));
        System.out.println();
        return tableTriad;
    }

    //Принимает строку полиза, возвращает таблицу триад с добавленным элементом
    public static ArrayList<Triad> addTriad (ArrayList<String> list,String value, int i){
        int tmpLabel = labelIndex;
        tableTriad.add(new Triad("M"+labelIndex++, list.get(i-2), list.get(i-1), value, "M"+(tmpLabel+1), "M"+(tmpLabel-1)));
        list.remove(i-2);
        list.remove(i-1);
        list.set(i-2,"M"+tmpLabel);
        return tableTriad;
    }

    //метод который увеличивает значение метки (нужна метка, которая на момент вызова метода ещё не создана)
    public static String incLabel (String inputLabel){
        String outputLabel = inputLabel.substring(1);
        int value = Integer.parseInt(outputLabel)+ 1;
        outputLabel = "M"+value;
        return outputLabel;
    }

    //принимает строку полиза, возвращает таблицу триад, для данного полиза
    public static ArrayList formTriad(ArrayList<String> alist){
        boolean flagWhile = false;

        for (int i = 0; i < alist.size(); i++) {
            switch (alist.get(i)) {
                case Arithmetic.plus:
                    tableTriad = addTriad(alist, Arithmetic.plus, i);
                    i = 0;
                    break;
                case Arithmetic.min:
                    tableTriad = addTriad(alist, Arithmetic.min, i);
                    i = 0;
                    break;
                case Arithmetic.multi:
                    tableTriad = addTriad(alist, Arithmetic.multi, i);
                    i = 0;
                    break;
                case Arithmetic.div:
                    tableTriad = addTriad(alist, Arithmetic.div, i);
                    i = 0;
                    break;
                case Logic.unEqual:
                    tableTriad = addTriad(alist, Logic.unEqual, i);
                    i = 0;
                    break;
                case Logic.equals:
                    tableTriad = addTriad(alist, Logic.equals, i);
                    i = 0;
                    break;
                case Logic.moreEqual:
                    tableTriad = addTriad(alist, Logic.moreEqual, i);
                    i = 0;
                    break;
                case Logic.lessEqual:
                    tableTriad = addTriad(alist, Logic.lessEqual, i);
                    i = 0;
                    break;
                case Logic.more:
                    tableTriad = addTriad(alist, Logic.more, i);
                    i = 0;
                    break;
                case Logic.less:
                    tableTriad = addTriad(alist, Logic.less, i);
                    i = 0;
                    break;
                case Assign.assign:
                    tableTriad = addTriad(alist, Assign.assign, i);
                    break;
                case KeyWords.xdo:
                    if (tableTriad.isEmpty()) numLabelForDo = "M0";
                    else {numLabelForDo = incLabel(tableTriad.get(tableTriad.size()-1).getLabel1());}
                    i = 0;
                    break;
                case KeyWords.xwhile:
                    flagWhile = true;
                    i = 0;
                    break;
            }
        }

        if (flagWhile){
            tableTriad.get(tableTriad.size()-1).setLabel2(numLabelForDo);
        }

        return tableTriad;
    }
}
