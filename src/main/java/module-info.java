module at.dimaweb {
    requires transitive javafx.controls;
    requires lombok;
    requires java.desktop;
    requires java.sql;
    exports at.dimaweb.entity;
    exports at.dimaweb.boundary;
    exports at.dimaweb.control;
}
