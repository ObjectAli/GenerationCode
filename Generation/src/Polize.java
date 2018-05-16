import java.util.ArrayList;
import java.util.Stack;

public class Polize {
    private static ArrayList<String> out = new ArrayList<>();
    private static Stack<Priority> work = new Stack<>();
    private static int chPoliz = 0;

    //Формирование объектов операций по приоритету и формирование строки полиза
    public static ArrayList calcPoliz(ArrayList<String>  list){

        Priority plus  = new Priority("+",5);
        Priority minus = new Priority("-",5);
        Priority multi = new Priority("*",6);
        Priority div   = new Priority("/",6);
        Priority open  = new Priority("(",0);
        Priority close = new Priority(")",1000);
        Priority unEqual = new Priority("!=",3);
        Priority equally = new Priority("==",3);
        Priority moreEqual = new Priority(">=",3);
        Priority lessEqual = new Priority("<=",3);
        Priority more = new Priority(">",3);
        Priority less = new Priority("<",3);
        Priority assign = new Priority(":=",2);
        Priority jwhile  = new Priority("while",0);
        Priority input = new Priority("INPUT",1);
        Priority output   = new Priority("OUTPUT",1);
        Priority end   = new Priority("",0);

        for(int i = 0; i < list.size(); i++){
            switch(list.get(i)){
                case "+" : solution(plus);  break;
                case "-" : solution(minus); break;
                case "*" : solution(multi); break;
                case "/" : solution(div);   break;
                case "(" : solution(open);  break;
                case ")" : solution(close); break;
                case "!=" : solution(unEqual); break;
                case "==" : solution(equally);break;
                case ">=" : solution(moreEqual);break;
                case "<=" : solution(lessEqual);break;
                case ">" : solution(more);break;
                case "<" : solution(less);break;
                case ":=" : solution(assign);break;
                case "input" : solution(moreEqual);break;
                case "Output" : solution(moreEqual);break;
                case ";" : solution(end);  break;
                default : out.add(list.get(i)); break;
            }
        }
        chPoliz += 1;
        System.out.println("Poliz expression №"+ chPoliz +": ");

        for (int i = 0; i < out.size(); i++){
            if (out.get(i).equals("")) out.remove(i);
        }

        return out;
    }

    //Заполнение стека, по полученным объектам приоритетов операций и формирование строки полиза
    public static void solution(Priority obj){
        if(!work.isEmpty()){
            try{
                while(obj.getNumber() <= work.peek().getNumber()){
                    switch(obj.getName()){
                        case "(": work.push(obj); break;
                        case ")":
                        {
                            try{
                                while(work.peek().getName() != "("){
                                    out.add(work.pop().getName());
                                }
                                work.pop();
                            }catch(Exception e){}
                        } break;
                        default:
                        {
                            try{
                                out.add(work.pop().getName()); break;
                            }catch(Exception e){}
                        }
                    }
                }
            }catch(Exception e){}
            if(obj.getName() != ")"&&obj.getName() !="(")
                work.push(obj);
        }else work.push(obj);
    }
}
