CREATE OR REPLACE FUNCTION sigec.arraypathfinder(columnname jsonb, expr text)
 RETURNS integer
 LANGUAGE plpgsql
AS $function$
DECLARE
	query text;
    v_count integer;
begin
	query := REPLACE(expr, '$', CAST(columnname AS TEXT) );
	execute query into v_count;
    RETURN v_count;
END;
$function$