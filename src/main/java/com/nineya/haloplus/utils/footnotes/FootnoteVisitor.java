package com.nineya.haloplus.utils.footnotes;

public interface FootnoteVisitor {
    void visit(FootnoteBlock node);

    void visit(Footnote node);
}
