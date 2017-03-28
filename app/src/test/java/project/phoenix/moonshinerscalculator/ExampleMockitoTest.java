package project.phoenix.moonshinerscalculator;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import project.phoenix.moonshinerscalculator.ui.activity.correctstregth.DeterminationResultCorrectStrength;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExampleMockitoTest {
    @Mock
    Context mockContext;

    @Mock
    DeterminationResultCorrectStrength mockDetermTest;

    /**
     * Useless test :)
     */
    @Test
    public void testDeterm() {
        when(mockDetermTest.determResult("67.6", "22.4", mockContext)).thenReturn("67.09");
        assertEquals("67.09", mockDetermTest.determResult("67.6", "22.4", mockContext));
    }
}
