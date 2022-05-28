package com.marc_auberer.musicmanager.domain;

public abstract class AbstractRepository {

    public static final long AUTO_INC = -1;

    protected abstract void writeOut();

    protected abstract void reload();
}
