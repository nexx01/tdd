package com.example.tdd;

import com.example.tdd.domain.Car;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.shaded.org.bouncycastle.util.Characters;

import java.io.*;
import java.time.Instant;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
//@DataJpaTest(properties = {
//        "spring.datasource.url=jdbc:h2:mem:testdb",
//        "spring.jpa.hibernate.ddl-auto=create-drop",
//}, showSql = false)
    @DataJpaTest(properties = {
            "logging.level.org.springframework.transaction.interceptor = TRACE",
    },showSql = true,
    includeFilters = @ComponentScan.Filter( type = FilterType.ASSIGNABLE_TYPE, classes= {com.example.tdd.CarServise.class}))
@AutoConfigureTestDatabase
class CarRepositoryTest {

    @Autowired
    private CarRepository subj;

    @Autowired
    private
    CarServise carServise;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void getCar_returnsCarDetails() {

//        Stream.of("1","2","3","4","5","6","7","8","9")
//                .map()


        var epochMilli = Instant.now().toEpochMilli();
        entityManager.persistAndFlush(new Car("prius", "hybrid"));
        var car = subj.findByName("prius");

        assertThat(car.getName()).isEqualTo("prius");
        assertThat(car.getType()).isEqualTo("hybrid");
        Arrays.sort(new int[1][1], (o1, o2) -> o1[0] - o2[0]);
        List.of().toArray();
//        Arrays.deepToString()
    }

    @Test
    void seeTransaction() {
        entityManager.persistAndFlush(new Car("prius", "hybrid"));
        var car = carServise.getCarsDetailsWithoutCache("prius");
        assertThat(car.getName()).isEqualTo("prius");
    }



    @Test
    void seeTransactionInStream() {
        Stream.iterate(0, i -> i < 1000, i -> ++i)
                .forEach(i ->   entityManager.persistAndFlush(new Car(Long.valueOf(i),"prius", "hybrid")));
        entityManager.clear();

        //----------------------------------------------------------------

        Stream.iterate(0, i -> i < 1000, i -> ++i)
                .forEach(i -> carServise.getCarsDetailsWithoutCache(Long.valueOf(i)));

        var car = carServise.getCarsDetailsWithoutCache("prius");
        assertThat(car.getName()).isEqualTo("prius");
    }


    @Test
    void seeTransactionInForeach() {
        var entity = new Car("prius", "hybrid");

        entityManager.persistAndFlush(entity);

        var list = Stream.iterate(0, i -> i < 1000, i -> ++i)
                .map(i -> entity.getId())
                .toList();

        list.forEach(
                name -> {
                    try {
                        carServise.getCarsDetailsWithoutCache(name);
                    } catch (Exception e) {

                    }
                }
        );

        var car = carServise.getCarsDetailsWithoutCache("prius");
        assertThat(car.getName()).isEqualTo("prius");
    }


    @Test
    void name() {
        var i = 1 ^ 2;
        System.out.println(1^5);
//        assertThat(1 ^ 5).isEqualTo(4);
//        assertThat(2 ^ 5).isEqualTo(7);
//        assertThat(9 ^ 5).isEqualTo(6);
//        assertThat(9 ^ 5).isEqualTo(6);


        System.out.println(1^5);
        System.out.println(2^6);
        System.out.println(11^15);
        System.out.println(15^11);

        System.out.println("---");
        System.out.println(5^6);
        System.out.println(1^6+1^5);

        System.out.println(5^16);
        System.out.println(1^16+1^5);


        System.out.println(115^16);
        System.out.println(1^16+1^115);
        }
    }


 class Main {

    /*
	Для чтения входных данных необходимо получить их
	из стандартного потока ввода (System.in).
	Данные во входном потоке соответствуют описанному
	в условии формату. Обычно входные данные состоят
	из нескольких строк. Можно использовать более производительные
	и удобные классы BufferedReader, BufferedWriter, Scanner, PrintWriter.

	С помощью BufferedReader можно прочитать из стандартного потока:
	* строку -- reader.readLine()
	* число -- int n = Integer.parseInt(reader.readLine());
	* массив чисел известной длины (во входном потоке каждое число на новой строке) --
	int[] nums = new int[len];
    for (int i = 0; i < len; i++) {
        nums[i] = Integer.parseInt(reader.readLine());
    }
	* последовательность слов в строке --
	String[] parts = reader.readLine().split(" ");

	Чтобы вывести результат в стандартный поток вывода (System.out),
	Через BufferedWriter можно использовать методы
	writer.write("Строка"), writer.write('A') и writer.newLine().

	Возможное решение задачи "Вычислите сумму чисел в строке":
	int sum = 0;
    String[] parts = reader.readLine().split(" ");
    for (int i = 0; i < parts.length; i++) {
        int num = Integer.parseInt(parts[i]);
        sum += num;
    }
    writer.write(String.valueOf(sum));
	*/
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
  int[] nums=new int[3];
        for (int i = 0; i < 3; i++) {
            nums[i] = Integer.parseInt("2 3 4");
        }

        System.out.println(Arrays.toString(nums));
    }

}