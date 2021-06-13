package com.example.campybehappy;

import com.example.campybehappy.Controller.Pages.LoginActivity;
import com.example.campybehappy.Controller.Pages.RegisterActivity;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    LoginActivity loginActivity = new LoginActivity();
    RegisterActivity registerActivity = new RegisterActivity();
    @Test
    public void valideLoginEmail() {
      //assertEquals(true, loginActivity.chekEmail("jbelimohamedaligmail.com"));
        assertEquals(true, loginActivity.chekEmail("jbelimohamedali@gmail.com"));

    }
    @Test
    public void validLoginPasswored() {
        assertEquals(true, loginActivity.chekpasswoored("123456789"));
    }
    @Test
    public void valideUserNeme() {
        assertEquals(true, registerActivity.chekUserName("dali"));
        //assertEquals(true, registerActivity.chekUserName("dali"));
    }
    @Test
    public void valideFullName() {
        assertEquals(true, registerActivity.chekFullName("testpass"));
    }
    @Test
    public void valideRegisterEmail() {
        assertEquals(true, registerActivity.chekEmail("mohamed@gmail.com"));
    }
    @Test
    public void validRegisterPasswored() {
        assertEquals(true, registerActivity.chekPasswored("0000"));
    }

}