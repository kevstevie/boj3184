import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class BOJ3184 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        for (int i = 0; i < r; i++) {
            String input = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = input.charAt(j);
            }
        }
        visit = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (visit[i][j]) continue;
                if (map[i][j] == '#') {
                    visit[i][j] = true;
                    continue;
                }
                dfs(i, j);
            }
        }
        System.out.println(totalSheep+" "+totalWolf);

    }
    static char[][] map;
    static boolean[][] visit;
    static Stack<Node> dfsStack = new Stack<>();
    static int countSheep = 0;
    static int countWolf = 0;
    static int totalSheep = 0;
    static int totalWolf = 0;

    static void dfs(int r, int c){
        int[] dx = {1, 0, -1 ,0};
        int[] dy = {0, 1, 0, -1};
        dfsStack.push(new Node(r,c,map[r][c]));
        visit[r][c] = true;
        while (!dfsStack.isEmpty()) {
            Node now = dfsStack.pop();
            counting(now.ch);
            for (int i = 0; i < 4; i++) {
                int x = now.x+dx[i];
                int y = now.y+dy[i];
                if(x<0 || x>=map.length || y<0 || y>=map[0].length) continue;
                if(visit[x][y]) continue;
                if(map[x][y] == '#'){
                    visit[x][y] = true;
                    continue;
                }
                dfsStack.push(new Node(x,y,map[x][y]));
                visit[x][y] = true;
            }
        }
        compare(countSheep, countWolf);
    }
    static void compare(int o1, int o2){
        if(o1>o2){
            totalSheep += countSheep;
            countSheep = 0;
            countWolf = 0;
        } else{
            totalWolf += countWolf;
            countWolf = 0;
            countSheep = 0;
        }
    }

    static void counting(char ch){
        if(ch == 'o') countSheep++;
        if(ch == 'v') countWolf++;
    }
}
class Node{
    int x;
    int y;
    char ch;

    public Node(int x, int y, char ch) {
        this.x = x;
        this.y = y;
        this.ch = ch;
    }
}
