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

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author amresh.singh
 * 
 */

@Embeddable
public class ProfessionalDataDTO
{

    @Column(name = "degree")
    private String degree;

    @Column(name = "experience")
    private String experience;

    @Column(name = "company")
    private String company;

    @Column(name = "primary_skill")
    private String primarySkill;

    /**
     * @return the degree
     */
    public String getDegree()
    {
        return degree;
    }

    /**
     * @param degree
     *            the degree to set
     */
    public void setDegree(String degree)
    {
        this.degree = degree;
    }

    /**
     * @return the experience
     */
    public String getExperience()
    {
        return experience;
    }

    /**
     * @param experience
     *            the experience to set
     */
    public void setExperience(String experience)
    {
        this.experience = experience;
    }

    /**
     * @return the company
     */
    public String getCompany()
    {
        return company;
    }

    /**
     * @param company
     *            the company to set
     */
    public void setCompany(String company)
    {
        this.company = company;
    }

    /**
     * @return the primarySkill
     */
    public String getPrimarySkill()
    {
        return primarySkill;
    }

    /**
     * @param primarySkill
     *            the primarySkill to set
     */
    public void setPrimarySkill(String primarySkill)
    {
        this.primarySkill = primarySkill;
    }

}
