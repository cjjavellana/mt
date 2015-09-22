# A Multi-Tenant App

A multi-tenant application is an application that can support multiple customers running on a single application instance.  
To provide this type of flexibility to tenants sharing the same platform, each tenant must be allowed to deploy their own  
customizations without affecting other tenants.

# Characteristics

This multi-tenant application architecture must have the following characteristics:  

1. Each tenant must have an isolated schema or database.  
2. Each tenant must be allowed to override the default system behavior.  
3. Each tenant must be able to extend and/or modify their own schema according to their own requirements.  
4. On the UI part, each tenant must be able to provide their own customization and/or add their own pages.  

# Technical Requirements

1. Must be able to provision / un-provision a tenant without downtime.  
2. Must be able to deploy updates to a tenant without downtime.
3. Must only have a single SessionFactory. We could not afford to have one SessionFactory / Tenant as it'll severely  
   impact memory utilization.


 