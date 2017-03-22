package project.phoenix.moonshiterscalculator;

import android.content.Context;
import android.test.mock.MockContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import project.phoenix.moonshiterscalculator.ui.activity.correctstregth.DeterminationResultCorrectStrength;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExampleMockTest {
    @Test
    public void testDeterm() {
        DeterminationResultCorrectStrength mockDetermTest = Mockito.mock(DeterminationResultCorrectStrength.class);
        Context falseContext = Mockito.mock(MockContext.class);
        when(mockDetermTest.determResult("67.6", "22.4", falseContext)).thenReturn("67.09");
        assertEquals("67.09", mockDetermTest.determResult("67.6", "22.4", falseContext));
    }
}
