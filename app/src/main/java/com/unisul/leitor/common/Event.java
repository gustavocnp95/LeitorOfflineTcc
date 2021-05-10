package com.unisul.leitor.common;

import androidx.annotation.NonNull;

import java.util.Optional;

public class Event<T> {
    @NonNull
    private final T mContent;
    private boolean hasBeenHandled = false;

    public Event(@NonNull final T content) {
        if (content == null) {
            throw new IllegalArgumentException("null values in Event are not allowed.");
        }
        mContent = content;
    }

    @NonNull
    public Optional<T> getContentIfNotHandled() {
        if (hasBeenHandled) {
            return Optional.empty();
        } else {
            hasBeenHandled = true;
            return Optional.of(mContent);
        }
    }

    @NonNull
    public T getContent() {
        return mContent;
    }

    public boolean hasBeenHandled() {
        return hasBeenHandled;
    }
}