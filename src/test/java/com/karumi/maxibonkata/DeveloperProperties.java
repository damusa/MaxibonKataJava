/*
 * Copyright (C) 2016 Karumi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.karumi.maxibonkata;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(JUnitQuickcheck.class) public class DeveloperProperties {

  private KarumiHQs karumiHQs;
  private Chat chat;
  private String ANY_NAME = "Pedro";

  @Before public void setUp() {

    System.out.println("Ejecutando el setup!!! ");

    chat = mock(Chat.class);
    karumiHQs = new KarumiHQs(chat);
  }


  @Property public void theNumberOfMaxibonsCanNotBeNegative(
          String developeName, int numberOfMaxibons) {

    Developer developer = new Developer(developeName, numberOfMaxibons);

    System.out.println(developer + " -> " + numberOfMaxibons);

    assertTrue(developer.getNumberOfMaxibonsToGrab() >= 0);

  }

  @Test public void theNumberOfMaxibonsIsRight() {

    assertEquals(Karumies.PEDRO.getNumberOfMaxibonsToGrab(), 3);
    assertEquals(Karumies.ALBERTO.getNumberOfMaxibonsToGrab(), 1);
    assertEquals(Karumies.DAVIDE.getNumberOfMaxibonsToGrab(), 0);
    assertEquals(Karumies.SERGIO.getNumberOfMaxibonsToGrab(), 2);
    assertEquals(Karumies.JORGE.getNumberOfMaxibonsToGrab(), 1);

  }

  @Property public void theNumberOfMaxibonsAlwaysGreaterThan2(
          List<@From(KarumiesGenerator.class) Developer> developers) {

    for (Developer developer : developers) {
      karumiHQs.openFridge(developer);
      System.out.println(developer + " opened bridge -> left " + karumiHQs.getMaxibonsLeft());

      assertTrue(karumiHQs.getMaxibonsLeft() > 2);
    }

    assertTrue(karumiHQs.getMaxibonsLeft() > 2);

  }

  @Property public void theNumberOfMaxibonsCanNeverBeLowerThanTwo(
          @From(DevelopersGenerator.class) Developer developer) {

    karumiHQs.openFridge(developer);

    System.out.println(developer + " opened bridge -> left " + karumiHQs.getMaxibonsLeft());

    assertTrue(karumiHQs.getMaxibonsLeft() > 2);
  }

  @Property public void theNumberOfMaxibonsCanNeverBeLowerThanTwoAtKarumi(
          @From(KarumiesGenerator.class) Developer developer) {

    karumiHQs.openFridge(developer);

    System.out.println(developer + " opened bridge -> left " + karumiHQs.getMaxibonsLeft());

    assertTrue(karumiHQs.getMaxibonsLeft() > 2);
  }



  /*
  @Test public void theNumberOfInitialMaxibonsAreTen() {
    assertEquals(10, karumiHQs.getMaxibonsLeft());
  }

  @Property public void theNumberOfMaxibonsCanNeverBeLowerThanTwo(
      @From(DevelopersGenerator.class) Developer developer) {
    karumiHQs.openFridge(developer);

    assertTrue(karumiHQs.getMaxibonsLeft() > 2);
  }

  @Property public void ifThereAreLessThanTwoMaxibonLeft10MaxibonsAreAutomaticallyBought(
      @From(HungryDevelopersGenerator.class) Developer developer) {
    int initialMaxibons = karumiHQs.getMaxibonsLeft();
    karumiHQs.openFridge(developer);

    int expectedMaxibons =
        getMaxibonsAfterOpeningTheFridge(initialMaxibons, developer.getNumberOfMaxibonsToGrab());
    assertEquals(expectedMaxibons, karumiHQs.getMaxibonsLeft());
  }

  @Property public void ifThereAreLessThanTwoMaxibonsLeftAMessageIsSentRequestingMore(
      @From(HungryDevelopersGenerator.class) Developer developer) {
    karumiHQs.openFridge(developer);

    verify(chat).sendMessage("Hi guys, I'm " + developer.getName() + ". We need more maxibons!");
  }

  @Property public void ifThereAreMoreThanTwoMaxibonsLeftNoMessagesAreSentOrderingMore(
      @From(NotSoHungryDevelopersGenerator.class) Developer developer) {
    karumiHQs.openFridge(developer);

    verify(chat, never()).sendMessage(
        "Hi guys, I'm " + developer.getName() + ". We need more maxibons!");
  }

  @Property public void ifSomeKarumiesGoToTheKitchenTheNumberOfMaxibonsCanNotBeLowerThanTwo(
      List<@From(KarumiesGenerator.class) Developer> developers) {
    karumiHQs.openFridge(developers);
    assertTrue(karumiHQs.getMaxibonsLeft() > 2);
  }

  @Property public void ifSomeKarumiesGoToTheKitchenTheNumberOfMaxibonsShouldBeTheExpectedOne(
      List<@From(KarumiesGenerator.class) Developer> developers) {
    int initialMaxibons = karumiHQs.getMaxibonsLeft();
    karumiHQs.openFridge(developers);

    int expectedMaxibons = calculateMaxibonsLeft(initialMaxibons, developers);
    assertEquals(expectedMaxibons, karumiHQs.getMaxibonsLeft());
  }

  private int calculateMaxibonsLeft(int initialMaxibons, List<Developer> developers) {
    int maxibonsLeft = initialMaxibons;
    for (Developer developer : developers) {
      maxibonsLeft -= developer.getNumberOfMaxibonsToGrab();
      if (maxibonsLeft < 0) {
        maxibonsLeft = 0;
      }
      if (maxibonsLeft <= 2) {
        maxibonsLeft += 10;
      }
    }
    return maxibonsLeft;
  }

  private int getMaxibonsAfterOpeningTheFridge(int initialMaxibons, int numberOfMaxibonsToGrab) {
    int maxibonsLeft = initialMaxibons;
    maxibonsLeft -= numberOfMaxibonsToGrab;
    if (maxibonsLeft < 0) {
      maxibonsLeft = 0;
    }
    if (maxibonsLeft <= 2) {
      maxibonsLeft += 10;
    }
    return maxibonsLeft;
  }

*/

}


