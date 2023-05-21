package it.indra.SigecAPI.util;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.javers.core.diff.changetype.PropertyChangeMetadata;
import org.javers.core.diff.changetype.container.ContainerElementChange;
import org.javers.core.diff.changetype.container.SetChange;
import org.javers.core.diff.changetype.container.ValueAdded;
import org.javers.core.diff.changetype.container.ValueRemoved;
import org.javers.core.diff.custom.CustomPropertyComparator;
import org.javers.core.metamodel.property.Property;

public class DateCustomComparator implements CustomPropertyComparator<Date, SetChange> {

	@Override
	public boolean equals(Date a, Date b) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if(a == null && b == null) {
			return true;
		}else if(a != null && b == null) {
			return false;
		}else if(a == null && b != null) {
			return false;
		}
		String aS = df.format(a);
		String bS = df.format(b);
		return aS.equals(bS);
	}

	@Override
	public String toString(Date value) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(value);
	}

	@Override
	public Optional<SetChange> compare(Date left, Date right, PropertyChangeMetadata metadata, Property property) {
		if (equals(left, right)) {
            return Optional.empty();
        }
		List<ContainerElementChange> changes = new ArrayList<ContainerElementChange>();	
		changes.add(new ValueRemoved(left));
		changes.add(new ValueAdded(right));
		return Optional.of(new SetChange(metadata, changes));
	}

}
