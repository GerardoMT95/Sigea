DO $$ DECLARE
    tabname RECORD;
BEGIN
    FOR tabname IN (SELECT tablename 
                    FROM pg_tables 
                    WHERE schemaname = 'sigec') 
LOOP
    EXECUTE 'TRUNCATE TABLE sigec.' || quote_ident(tabname.tablename) || ' CASCADE';
END LOOP;
END $$