package com.ley.spring.boot;

import com.google.common.collect.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

@Slf4j
public class TreeSetTest {

    @Test
    public void testTreeSet() {
        List<Person> persons = new ArrayList<>(10);
        Comparator<Person> comparator = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return (o1.getCycleDay().compareTo(o2.getCycleDay()));
            }
        };
        SortedMultiset<Person> set = TreeMultiset.create(comparator);
        persons.add(new Person("刘恩源", "2019-6-19"));
        persons.add(new Person("刘恩源1", "2019-6-20"));
        persons.add(new Person("刘恩源2", "2019-6-21"));
        persons.add(new Person("刘恩源3", "2019-6-22"));
        persons.add(new Person("刘恩源4", "2019-6-23"));
        persons.add(new Person("刘恩源5", "2019-6-24"));
        persons.add(new Person("刘恩源6", "2019-6-25"));
        persons.add(new Person("刘恩源7", "2019-6-22"));
        set.addAll(persons);
        System.out.println(set.size());
        Person currentPerson = new Person(null, "2019-6-22");
        Person nextPerson=new Person(null,"2019-6-23");

        SortedMultiset<Person> lessThenPersonSet = set.headMultiset(currentPerson, BoundType.OPEN);
        SortedMultiset<Person> greaterThanPersonSet = set.tailMultiset(currentPerson, BoundType.OPEN);
        SortedMultiset<Person> currentPersonSet = set.subMultiset(currentPerson, BoundType.CLOSED, nextPerson, BoundType.OPEN);
        System.out.println(lessThenPersonSet);
        System.out.println(greaterThanPersonSet);
        System.out.println(currentPersonSet);
    }
}
