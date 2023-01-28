package swea1948;
import java.util.Scanner;

class Solution
{
    public static void main(String args[]) throws Exception {
        //Scanner 선언
        Scanner sc = new Scanner(System.in);
        int T;
        //테스트 케이스 입력
        T=sc.nextInt();

        //1부터 입력받은 T까지 반복
        for(int test_case = 1; test_case <= T; test_case++) {

            //월 일로 이루어진 날짜를 2개 입력받으므로 크기가 4인 배열 생성
            int[] arr = new int[4];

            //배열 요소 입력
            for (int i = 0; i < arr.length; i++) {
                arr[i] = sc.nextInt();
            }

            //풀이
            int day = 0;
            //월이 같은 경우
            if (arr[0] == arr[2]) {
                //일끼리 뺀 후 1을 더한다
                day = arr[3] - arr[1] + 1;
            }
            //월이 같지 않은 경우
            else {
                //첫번째 달부터 두번째 달까지 반복한다.
                for (int i = arr[0]; i < arr[2]; i++) {
                    //월을 입력하면 각각의 월마다의 날짜 수를 더해준다.
                    switch (i) {
                        case 1:
                            day += 31;
                            break;
                        case 2:
                            day += 28;
                            break;
                        case 3:
                            day += 31;
                            break;
                        case 4:
                            day += 30;
                            break;
                        case 5:
                            day += 31;
                            break;
                        case 6:
                            day += 30;
                            break;
                        case 7:
                            day += 31;
                            break;
                        case 8:
                            day += 31;
                            break;
                        case 9:
                            day += 30;
                            break;
                        case 10:
                            day += 31;
                            break;
                        case 11:
                            day += 30;
                            break;
                        case 12:
                            day += 31;
                            break;
                        default:
                            break;
                    }
                }
                //더해진 날짜 수에서 첫번째 날짜 수는 빼고 두번째 날짜 수는 더한 뒤 1을 더한다.
                day = day - arr[1] + 1 + arr[3];
            }

            //출력
            System.out.println("#" + test_case + " " + day);
        }
    }
}