DO $$ DECLARE
    sequence RECORD;
BEGIN
    FOR sequence IN (
    	select sequence_name 
    	from information_schema."sequences" 
    	where sequence_catalog='admin' 
    	and sequence_schema='sigec'
    ) 
LOOP
    EXECUTE 'ALTER SEQUENCE sigec.' || quote_ident(sequence.sequence_name) ||  ' RESTART';
END LOOP;
END $$