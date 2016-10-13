package com.example.yuly.githubclient;

import android.content.Context;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TestedTest {
    private static final String FAKE_STRING = "GitHubClient";
//    @Mock
//    Context mMockContext;

    @Test
    public void readStringFromContext_LocalizedString() {
        Context mMockContext = mock(Context.class);

        // Given a mocked Context injected into the object under test...
        when(mMockContext.getString(R.string.app_name)).thenReturn(FAKE_STRING);

        Tested tested = new Tested(mMockContext);

        // ...when the string is returned from the object under test...
        String result = tested.getStr();

        // ...then the result should be the expected one.
        assertThat(result, is(FAKE_STRING));

        verify(mMockContext).getString(R.string.app_name);
    }
}