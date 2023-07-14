package application;

import entities.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter full file path: ");
        String path = sc.next();

        System.out.println("What`s the salary price ?");
        Double salaryPrice = sc.nextDouble();

        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            List<Employee> list = new ArrayList<>();

            String line = br.readLine();
            while ((line != null)){
                String [] fields = line.split(",");
                list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
                line = br.readLine();
            }

            System.out.println("Emails of people whose wage is over 2000.00:");

            List<String> salaries = list.stream()
                    .filter(x -> x.getSalary() > salaryPrice )
                    .map(x -> x.getEmail())
                    .sorted()
                    .collect(Collectors.toList());
            salaries.forEach(System.out::println);

            Double sum = list.stream()
                    .filter(x -> x.getName().charAt(0) == 'M')
                    .map(x -> x.getSalary())
                    .reduce(0.0, (x,y) -> x + y);
            System.out.println("All wages whose holder has the letter M: " + sum);

        }
        catch (IOException e){
            System.out.println("Error " +  e.getMessage());
        }
        sc.close();
    }
}
