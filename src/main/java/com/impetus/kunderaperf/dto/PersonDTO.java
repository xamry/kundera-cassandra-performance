/*******************************************************************************
 * * Copyright 2011 Impetus Infotech.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 ******************************************************************************/

package com.impetus.kunderaperf.dto;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author amresh.singh
 * 
 */

@Entity
@Table(name = "Person", schema = "KunderaKeyspace")
public class PersonDTO
{

    @Id
    private String personId;

    @Embedded
    private PersonalDataDTO personalData;

    @Embedded
    private ProfessionalDataDTO professionalData;

    /**
     * @return the personId
     */
    public String getPersonId()
    {
        return personId;
    }

    /**
     * @param personId
     *            the personId to set
     */
    public void setPersonId(String personId)
    {
        this.personId = personId;
    }

    /**
     * @return the personalData
     */
    public PersonalDataDTO getPersonalData()
    {
        return personalData;
    }

    /**
     * @param personalData
     *            the personalData to set
     */
    public void setPersonalData(PersonalDataDTO personalData)
    {
        this.personalData = personalData;
    }

    /**
     * @return the professionalData
     */
    public ProfessionalDataDTO getProfessionalData()
    {
        return professionalData;
    }

    /**
     * @param professionalData
     *            the professionalData to set
     */
    public void setProfessionalData(ProfessionalDataDTO professionalData)
    {
        this.professionalData = professionalData;
    }

}
