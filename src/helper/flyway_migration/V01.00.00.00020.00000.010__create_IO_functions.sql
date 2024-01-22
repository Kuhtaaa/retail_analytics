CREATE OR REPLACE FUNCTION import_db (t_name varchar, separator VARCHAR DEFAULT ',') RETURNS VARCHAR AS
$$
DECLARE
    path TEXT = '/app/import/';
    str TEXT;
    name TEXT;
BEGIN
    EXECUTE format('COPY %s FROM ''%s%s.csv'' DELIMITER ''%s'' CSV;', t_name, path, t_name, separator);

    name :=
        (SELECT column_name
        FROM information_schema.columns
        WHERE table_schema = 'public' AND table_name = t_name AND is_identity = 'YES');

    IF name IS NOT NULL THEN
    str :=  'SELECT setval(''' || t_name || '_' || name || '_seq'', max('|| name ||')) FROM ' || t_name;
    EXECUTE (str);
    END IF;
    RETURN 'IMPORT SUCCESS INTO TABLE ' || t_name;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION export_db(name VARCHAR, sep VARCHAR = ',') RETURNS BOOLEAN AS
$$
DECLARE
    str TEXT;
BEGIN
    str := 'copy ' || name || ' to ''/app/export/' || name || '.csv'' with csv delimiter ''' || sep ||
        ''' header';
    EXECUTE (str);
    RETURN TRUE;
END;
$$ LANGUAGE plpgsql;
