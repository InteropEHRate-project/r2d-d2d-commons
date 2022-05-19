package eu.interopehrate.protocols.server;

import org.hl7.fhir.r4.model.Bundle;

import java.util.ArrayList;
import java.util.Date;

import eu.interopehrate.protocols.common.ResourceCategory;

public class ResourceServerAdapter implements ResourceServerListener {


    @Override
    public Bundle onResourcesRequested(String... ids) throws Exception {
        return null;
    }

    @Override
    public Bundle onResourcesRequested(Date from, boolean isSummary) throws Exception {
        return null;
    }

    @Override
    public Bundle onResourcesRequested(Date from, boolean isSummary, ResourceCategory... categories) throws Exception {
        final Bundle mergedBundle = new Bundle();
        final ArrayList<String> insertedResources = new ArrayList<String>();
        Bundle bundle = new Bundle();
        
        for (ResourceCategory category : categories) {
            bundle = onResourcesRequested(category, null, null, from, isSummary);
            for (Bundle.BundleEntryComponent entry : bundle.getEntry()) {
                if (!insertedResources.contains(entry.getId())) {
                    mergedBundle.addEntry().setResource(entry.getResource());
                    insertedResources.add(entry.getId());
                }
            }
        }

        insertedResources.clear();

        return mergedBundle;
    }

    @Override
    public Bundle onResourcesRequested(ResourceCategory category, String subCategory, String type, Date from, boolean isSummary) throws Exception {
        return null;
    }

    @Override
    public Bundle onResourcesRequested(ResourceCategory category, String subCategory, String type, int mostRecentSize, boolean isSummary) throws Exception {
        return null;
    }

    @Override
    public void onResourcesReceived(Bundle healthDataBundle) throws Exception {

    }
}
